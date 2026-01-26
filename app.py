import streamlit as st
import PyPDF2 as pdf
from dotenv import load_dotenv
from langchain_groq import ChatGroq
import os

load_dotenv()

# ---------- LLM Function (ChatGroq) ----------

def get_groq_response(input_text):
    model = ChatGroq(
        model="llama-3.3-70b-versatile",
        temperature=0.6
    )
    response = model.invoke(input_text)
    return response.content

# ---------- PDF Reader ----------

def input_pdf_text(upload_file):
    reader = pdf.PdfReader(upload_file)
    text = ""
    for page in reader.pages:
        text += page.extract_text() or ""
    return text

# ---------- Prompt ----------

input_prompt = """
You are a highly experienced Application Tracking System (ATS) used by top tech companies.

TASK:
Analyze the provided resume against the given job description and evaluate how well the resume matches the job requirements.

INSTRUCTIONS:
- Respond in clear, well-structured PLAIN TEXT only
- Do NOT use JSON
- Do NOT use curly braces
- Do NOT use key-value format
- Do NOT use code blocks
- Do NOT include introductions or conclusions
- Be precise and professional

FORMAT THE RESPONSE EXACTLY AS BELOW:

JD Match Percentage:
<give a percentage from 0 to 100>

Missing or Weak Keywords:
- list important skills, tools, or keywords missing from the resume

Strengths:
- list the strongest matching skills or experiences

Profile Summary:
Write a concise professional summary (4–6 lines) explaining how suitable the candidate is for this role and what can be improved.

INPUT DATA:
Resume:
{text}

Job Description:
{jd}
"""

# ---------- Streamlit UI ----------

st.title("Smart ATS")
st.text("Improve your resume ATS")

jd = st.text_area("Paste the job description")
uploaded_file = st.file_uploader("Upload your resume", type="pdf")
submit = st.button("Submit")

# ---------- Button Logic ----------

if submit:
    if uploaded_file is None:
        st.warning("Please upload a resume PDF.")
    elif not jd.strip():
        st.warning("Please paste a job description.")
    else:
        resume_content = input_pdf_text(uploaded_file)
        formatted_prompt = input_prompt.format(
            text=resume_content,
            jd=jd
        )

        response = get_groq_response(formatted_prompt)

        st.subheader("ATS Evaluation")
        st.write(response)


