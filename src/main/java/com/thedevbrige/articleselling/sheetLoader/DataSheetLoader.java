package com.thedevbrige.articleselling.sheetLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.lang.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Service;

import com.thedevbrige.articleselling.domain.Pays;
import com.thedevbrige.articleselling.domain.Ville;
import com.thedevbrige.articleselling.repository.PaysRepository;
import com.thedevbrige.articleselling.repository.VilleRepository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by gims on 25/12/15.
 */
@Service
public class DataSheetLoader {

    @Inject
    PaysService paysService;
    @Inject
    VillesService villesService;
    @Inject
    PaysRepository paysRepository;

    String dataSheetFile = "collection.xls";
    InputStream stream = DataSheetLoader.class.getResourceAsStream("/" + dataSheetFile);

    @PostConstruct
    public void loadDataSheet(){

        try {
            HSSFWorkbook workbook = new HSSFWorkbook(stream);
            List<Pays> listPays = paysRepository.findAll();
           if(listPays.isEmpty()){
	            updatePays(workbook);
	            updateVille(workbook);
	            updateVille2(workbook);
           }
            IOUtils.closeQuietly(stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(stream);
        }

    }
    public void updatePays(HSSFWorkbook workbook){
        HSSFSheet sheet = workbook.getSheet("pays");
        if(sheet==null) return;
        Iterator<Row> rowIterator =sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            paysService.update(row);
        }
    }
   public void updateVille(HSSFWorkbook workbook){
        HSSFSheet sheet =workbook.getSheet("villes");
        if(sheet==null) return;
        Iterator<Row> rowIterator =sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            villesService.update(row);
        }
    }
    public void updateVille2(HSSFWorkbook workbook){
        HSSFSheet sheet = workbook.getSheet("villes2");
        if(sheet==null) return;
        Iterator<Row> rowIterator =sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            villesService.update(row);
        }
    }
}
