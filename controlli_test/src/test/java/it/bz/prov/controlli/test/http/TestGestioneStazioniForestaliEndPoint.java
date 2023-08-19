package it.bz.prov.controlli.test.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.bz.prov.controlli.bo.StazioneForestaleBO;
import it.bz.prov.controlli.test.config.EndpointConfig;

public class TestGestioneStazioniForestaliEndPoint {
	
	/*
	@Test
	public void insertStazioneForestaleTest() {
		URL url;
		try {
			StazioneForestaleBO s = new StazioneForestaleBO();
			s.set_descrIT("Stazione Forestale Egna");
			s.set_descrDE("XXXX");
			s.set_annoValiditaInizio(2019);
			s.set_annoValiditaFine(9999);
				
			url = new URL(EndpointConfig.GESTIONE_STAZIONI_FORESTALI_ENDPOINT + "/insert");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("POST");
			ObjectMapper mapper = new ObjectMapper();
			String str =  mapper.writeValueAsString(s);
			byte[] outputInBytes = str.getBytes("UTF-8");
			OutputStream os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			System.out.println("insertStazioneForestaleTest: " + httpCon.getResponseMessage());
			if (httpCon.getResponseCode() == 200) {
				assertEquals(httpCon.getResponseMessage(), "OK");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail("Exception not thrown");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception not thrown");
		}
	}
	*/
	
	
	@Test
	public void modifyStazioneForestaleTest() {
		URL url;
		try {
			System.out.println("******* modify *******************");
			System.out.println("---- insert");
			StazioneForestaleBO s = new StazioneForestaleBO();
			s.set_id(55L);
			s.set_descrIT("Stazione Forestale Brunico");
			s.set_descrDE("XXXX");
			s.set_annoValiditaInizio(2018);
			s.set_annoValiditaFine(9999);
			s.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			s.set_userCreazione("XXXX"); 
			s.set_flagValido(1);
			
			url = new URL(EndpointConfig.GESTIONE_STAZIONI_FORESTALI_ENDPOINT + "/insert");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("POST");
			ObjectMapper mapper = new ObjectMapper();
			String str =  mapper.writeValueAsString(s);
			byte[] outputInBytes = str.getBytes("UTF-8");
			OutputStream os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			
			System.out.println("---- modify");
			
			s.set_descrDE("XXXXz");	
			s.set_idStazioneForestale(5L);
			 
			url = new URL(EndpointConfig.GESTIONE_STAZIONI_FORESTALI_ENDPOINT + "/modify");
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("PUT");
			mapper = new ObjectMapper();
			 str =  mapper.writeValueAsString(s);
			outputInBytes = str.getBytes("UTF-8");
			os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			
			System.out.println("modifyStazioneForestaleTest: " + httpCon.getResponseMessage());
			if (httpCon.getResponseCode() == 200) {
				assertEquals(httpCon.getResponseMessage(), "OK");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail("Exception not thrown");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception not thrown");
		}
		System.out.println("******* modify *******************");
	}
	
	
	/*
	@Test
	public void deleteStazioneForestaleTest() {
		URL url;
		try {
			System.out.println("******* delete *******************");
			System.out.println("---- insert");
			StazioneForestaleBO s = new StazioneForestaleBO();
			s.set_id(55L);
			s.set_idStazioneForestale(1);
			s.set_descrIT("Stazione Forestale Bressanone");
			s.set_descrDE("XXXX");
			s.set_annoValiditaInizio(2018);
			s.set_annoValiditaFine(9999);
			s.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			s.set_userCreazione("XXXX"); 
			s.set_flagValido(1);
			
			url = new URL(EndpointConfig.GESTIONE_STAZIONI_FORESTALI_ENDPOINT + "/insert");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("POST");
			ObjectMapper mapper = new ObjectMapper();
			String str =  mapper.writeValueAsString(s);
			byte[] outputInBytes = str.getBytes("UTF-8");
			OutputStream os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			System.out.println("---- delete");
				
			url = new URL(EndpointConfig.GESTIONE_STAZIONI_FORESTALI_ENDPOINT + "/delete");
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("DELETE");
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			mapper = new ObjectMapper();
			str =  mapper.writeValueAsString(s);
			outputInBytes = str.getBytes("UTF-8");
			os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			System.out.println("deleteStazioneForestaleTest: " + httpCon.getResponseMessage());
			if (httpCon.getResponseCode() == 200) {
				assertEquals(httpCon.getResponseMessage(), "OK");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail("Exception not thrown");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception not thrown");
		}

		System.out.println("******* delete *******************");
	}
	*/
	
	/*
	@Test
	public void readStazioneForestaleTest() {
		URL url;
		try {
			
			url = new URL(EndpointConfig.GESTIONE_STAZIONI_FORESTALI_ENDPOINT + "/getList");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("GET");

			System.out.println("readStazioneForestaleTest: " + httpCon.getResponseMessage());
			if (httpCon.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output = "";
				while ((output = br.readLine()) != null) {
					sb.append(output);

					System.out.println("----- " + output);
				}

				System.out.println("***** " + sb.toString());

				List<StazioneForestaleBO> list = Arrays.asList(new ObjectMapper().readValue(sb.toString(), StazioneForestaleBO[].class));
				

				System.out.println("***** STAZIONI FORESTALI NEL DB *****");
				for(StazioneForestaleBO s: list) {
					System.out.println("____________________________");
					System.out.println(s.toString());
				}
				System.out.println("***** --------- *****");

				assertEquals(httpCon.getResponseMessage(), "OK");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail("Exception not thrown");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception not thrown");
		}
	}
	*/


}
