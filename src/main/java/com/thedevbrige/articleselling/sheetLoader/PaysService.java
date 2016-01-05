package com.thedevbrige.articleselling.sheetLoader;

import com.thedevbrige.articleselling.domain.Pays;
import com.thedevbrige.articleselling.repository.PaysRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by gims on 25/12/15.
 */
@Service
public class PaysService {

    @Inject
    PaysRepository paysRepository;
    //Pays paysExist;

    public void update(Row row){

        Pays pays = new Pays();
        Cell cell = row.getCell(0);
        //paysExist = paysRepository.findByNamePays(cell.getStringCellValue());
        if(cell != null && StringUtils.isNotBlank(cell.getStringCellValue())){
        pays.setNamePays(cell.getStringCellValue().trim());
          }else{
        return;
          }
        paysRepository.save(pays);
   }
}
