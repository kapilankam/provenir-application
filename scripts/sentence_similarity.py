import spacy
import sys

nlp = spacy.load("en_core_web_md")

def calculate_similarity(sentence1, sentence2):
    doc1 = nlp(sentence1)
    doc2 = nlp(sentence2)

    similarity = doc1.similarity(doc2)
    return similarity

if __name__ == "__main__":

    sentence1 = sys.argv[1]
    sentence2 = sys.argv[2]

    similarity = calculate_similarity(sentence1, sentence2)
    print(f"{similarity}")