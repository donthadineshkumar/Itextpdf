import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/* GeneratePdf.java
 * Created on 26-Sep-2014
 */

public class CreatePdf {

	public static void main(String[] args) {

		try {

			// creating a file stream object
			OutputStream file = new FileOutputStream(new File(
					"/home/dinesh/CWCReport.pdf"));

			// creating a document object - with A4 size
			// please see the effect of rotate() method -with and without it

			// rotate() method -creates a pdf in landscape format
			Document document = new Document(PageSize.A4.rotate());

			// must create a PdfWriter object -pass document and file output
			// stream
			PdfWriter.getInstance(document, file);

			// creating document info
			document.addAuthor("Dinesh Dontha");
			document.addSubject("CWC Matches List");
			document.addTitle("CWC - Schedule");
			document.addCreationDate();

			// must open a document -to put content
			document.open();

			// creating a Phrase
			Phrase title = new Phrase("Cricket World Cup-2015- Schedule",
					new Font(FontFamily.HELVETICA, 32));

			// creating a Paragrapgh with Phrase as parameter
			Paragraph para1 = new Paragraph(title);
			para1.setAlignment(Element.ALIGN_CENTER);

			// adding paragraph to document -this main heading for document.
			document.add(para1);

			// adding a blank line after - heading/title
			document.add(Chunk.NEWLINE);

			// creating a paragraph
			Paragraph para2 = new Paragraph(
					"Next upcoming league matches of Team India in the "
							+ "giagantic Cricket World Cup-2015", new Font(
							FontFamily.HELVETICA, 16));
			para2.setAlignment(Element.ALIGN_LEFT);

			// adding a paragraph - this is infact the first paragraph in this
			// exxample
			document.add(para2);

			// adding a blank line after - first paragraph
			document.add(Chunk.NEWLINE);

			// adding all cell data to a Map object
			Map<String, String> indiaMatches = new HashMap<>();

			indiaMatches.put("Sunday, March 6th 2015", "INDIA VS WEST INDIES");
			indiaMatches.put("Sunday, March 10th 2015", "INDIA VS IRELAND");
			indiaMatches.put("Sunday, March 14th 2015", "INDIA Vs ZIMBABWE");

			// Define a PDF table with 2 columns - Match Date & Between column
			// Names
			PdfPTable table = new PdfPTable(2);

			// Setting global table cell properties
			table.setWidthPercentage(100);
			table.getDefaultCell().setPadding(10);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			// table header creation -using PdfPCell class

			// creating table header -cell -first column - Match Date
			Phrase matchDate = new Phrase("Match Date", new Font(
					FontFamily.HELVETICA, 14, Font.BOLD));
			// using PdfPCell class
			PdfPCell cellHeader1 = new PdfPCell(matchDate);
			cellHeader1.setBackgroundColor(BaseColor.ORANGE);
			cellHeader1.setPadding(10);
			cellHeader1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellHeader1);

			// creating table header -cell -second column - Between
			Phrase matchBetween = new Phrase("Match Between", new Font(
					FontFamily.HELVETICA, 14, Font.BOLD));
			// using PdfPCell class
			PdfPCell cellHeader2 = new PdfPCell(matchBetween);
			cellHeader2.setBackgroundColor(BaseColor.ORANGE);
			cellHeader2.setPadding(10);
			cellHeader2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellHeader2);

			// creating cell data -other than table column headers- using
			// addCell()
			// method of PdfPTable object
			for (Map.Entry<String, String> entry : indiaMatches.entrySet()) {
				table.addCell(entry.getKey());
				table.addCell(entry.getValue());
			}

			// adding table to Pdf document
			document.add(table);

			// finally close - document & file stream
			document.close();
			file.close();

		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
