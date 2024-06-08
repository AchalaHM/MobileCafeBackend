$word = New-Object -ComObject Word.Application
$doc = $word.Documents.Open("C:\Users\achalapu\Documents\cafeback\WORD6.docx")
$doc.SaveAs([ref] "C:\Users\achalapu\Documents\cafeback\WORD8.pdf", [ref] 17)
$doc.Close()
$word.Quit()
