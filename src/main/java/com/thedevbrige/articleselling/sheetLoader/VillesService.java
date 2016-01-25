package com.thedevbrige.articleselling.sheetLoader;

import com.thedevbrige.articleselling.domain.Pays;
import com.thedevbrige.articleselling.domain.Ville;
import com.thedevbrige.articleselling.repository.PaysRepository;
import com.thedevbrige.articleselling.repository.VilleRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by gims on 25/12/15.
 */
@Service
public class VillesService {
    @Inject
    VilleRepository villeRepository;
    @Inject
    PaysRepository paysRepository;

    //Ville villeExist;

    public void update(Row row){

        Ville ville = new Ville();
        Pays pays = new Pays();

        Cell cell = row.getCell(0);
        //villeExist = villeRepository.findByNameVille(cell.getStringCellValue());
        if(cell != null && StringUtils.isNotBlank(cell.getStringCellValue())){
            ville.setNameVille(cell.getStringCellValue().trim());
        }else{
            return;
        }
        cell = row.getCell(1);
        if(cell != null && StringUtils.isNotBlank(cell.getStringCellValue())){
            ville.setPays(paysRepository.findByNamePaysEquals(cell.getStringCellValue().trim()));
        }
        villeRepository.save(ville);
    }
}
