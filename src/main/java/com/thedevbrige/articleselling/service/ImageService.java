package com.thedevbrige.articleselling.service;


import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

	public String checkMime(byte[] img){
		Magic parser = new Magic() ;
		MagicMatch match;
		try {
			match = parser.getMagicMatch(img);
		} catch (MagicParseException e) {
			e.printStackTrace();
			return "application/jpg";
		} catch (MagicMatchNotFoundException e) {
			e.printStackTrace();
			return "application/jpg";
		} catch (MagicException e) {
			e.printStackTrace();
			return "application/jpg";
		}
		return match.getMimeType();
	}



}
