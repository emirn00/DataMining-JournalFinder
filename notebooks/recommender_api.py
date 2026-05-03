import pandas as pd
import sqlite3
import re
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import uvicorn

app = FastAPI(title="Journal Finder NLP Engine")

class RecommendRequest(BaseModel):
    abstract_text: str

# Global değişkenler
df = None
tfidf_matrix = None
vectorizer = None

def load_data():
    global df, tfidf_matrix, vectorizer
    try:
        conn = sqlite3.connect('CompSciencePub.sqlite')
        # Gerçek veritabanı şemasına göre query güncellendi
        query = """
        SELECT 
            ar.AcademicRecordID as id, 
            ar.Title as title, 
            ara.AbstractText as abstract, 
            p.Name as journal_name
        FROM AcademicRecord ar
        JOIN AcademicRecordAbstract ara ON ar.AcademicRecordID = ara.AcademicRecordId
        JOIN Publication p ON ar.PublicationId = p.PublicationID
        """
        df = pd.read_sql_query(query, conn)
        conn.close()

        if df.empty:
            print("!!! UYARI: Veritabanı boş!")
            return

        # Metin temizleme
        df['clean_abstract'] = df['abstract'].apply(lambda x: str(x).lower())
        
        # TF-IDF Modelini eğit
        vectorizer = TfidfVectorizer(stop_words='english', max_features=10000)
        tfidf_matrix = vectorizer.fit_transform(df['clean_abstract'])
        print(f"--- Model Başarıyla Eğitildi ---")
        print(f"Toplam Kayıt Sayısı: {len(df)}")
    except Exception as e:
        print(f"!!! Veri yükleme hatası: {e}")

@app.on_event("startup")
async def startup_event():
    load_data()

@app.post("/recommend")
async def recommend(request: RecommendRequest):
    if vectorizer is None:
        raise HTTPException(status_code=500, detail="NLP Engine is not initialized")
    
    print(f"\n--- Yeni İstek Geldi ---")
    
    clean_input = request.abstract_text.lower()
    input_vec = vectorizer.transform([clean_input])
    
    # Benzerlik hesapla
    similarities = cosine_similarity(input_vec, tfidf_matrix).flatten()
    related_indices = similarities.argsort()[::-1]
    
    recommendations = []
    seen_journals = set()
    
    print(f"En yüksek benzerlik skoru: {max(similarities) if len(similarities) > 0 else 0}")
    
    for idx in related_indices:
        score = similarities[idx]
        if score <= 0.01: continue 
        
        journal = df.iloc[idx]['journal_name']
        if journal not in seen_journals:
            recommendations.append(journal)
            seen_journals.add(journal)
            print(f"Önerilen: {journal} (Skor: {score:.4f})")
        
        if len(recommendations) == 5:
            break
            
    return recommendations

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=5001)
