from fpdf import FPDF

pdf = FPDF()
pdf.add_page()
pdf.set_font("Courier", size=10)

with open("AllClasses.txt", "r", encoding="utf-8") as f:
    for line in f:
        pdf.cell(0, 5, txt=line.rstrip(), ln=1)

pdf.output("AllClasses.pdf")
print("PDF created: AllClasses.pdf")
