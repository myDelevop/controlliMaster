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
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.bz.prov.controlli.bo.UtenteBO;
import it.bz.prov.controlli.test.config.EndpointConfig;

public class TestGestioneUtentiEndpoint {

	@Test
	public void insertUserTest() {
		URL url;
		try {
			UtenteBO utenteDaInserire = new UtenteBO();
			
			utenteDaInserire.set_cognome("TEST");
			utenteDaInserire.set_nome("TEST");
			utenteDaInserire.set_dominio("iconsuilting.test");
			utenteDaInserire.set_username("userTEST");
				
			
			url = new URL(EndpointConfig.GESTIONE_UTENTI_ENDPOINT + "/insertUtente");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("POST");
			ObjectMapper mapper = new ObjectMapper();
			String str =  mapper.writeValueAsString(utenteDaInserire);
			byte[] outputInBytes = str.getBytes("UTF-8");
			OutputStream os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			
			System.out.println("insertUserTest: " + httpCon.getResponseMessage());
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
	
	
	@Test
	public void updateUserTest() {
		URL url;
		try {
			UtenteBO utenteDaModificare = new UtenteBO();
			
			utenteDaModificare.set_id(37l);
			utenteDaModificare.set_nome("NOME-MODIFICATO");
			utenteDaModificare.set_username("USERNAME-MODIFICATO");
			utenteDaModificare.set_dominio("DOMINIO-MODIFICATO");

			
			url = new URL(EndpointConfig.GESTIONE_UTENTI_ENDPOINT + "/updateUtente" /*+ utenteDaModificare.get_id()*/);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("PUT");
			ObjectMapper mapper = new ObjectMapper();
			String str =  mapper.writeValueAsString(utenteDaModificare);
			byte[] outputInBytes = str.getBytes("UTF-8");
			OutputStream os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			
			System.out.println("updateUserTest: " + httpCon.getResponseMessage());
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

	
	@Test
	public void deleteUserTest() {
		URL url;
		try {
			Long idUtenteDaEliminare = 36L;
			
			url = new URL(EndpointConfig.GESTIONE_UTENTI_ENDPOINT + "/" + idUtenteDaEliminare);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("DELETE");
			
		
			System.out.println("deleteUserTest: " + httpCon.getResponseMessage());
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
	
	@Test
	public void findAllTest() {
		URL url;
		try {
			url = new URL(EndpointConfig.GESTIONE_UTENTI_ENDPOINT + "/findAllUtentiValidi");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("GET");

			System.out.println("findAllTest " + httpCon.getResponseMessage());
			if (httpCon.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				System.out.println(br.toString());
				StringBuilder sb = new StringBuilder();
				String output = "";
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}

				List<UtenteBO> list = Arrays.asList(new ObjectMapper().readValue(sb.toString(), UtenteBO[].class));
				

				System.out.println("***** UTENTI NEL DB *****");
				for(UtenteBO u: list) {
					System.out.println("____________________________");
					System.out.println(u.toString());
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

}
