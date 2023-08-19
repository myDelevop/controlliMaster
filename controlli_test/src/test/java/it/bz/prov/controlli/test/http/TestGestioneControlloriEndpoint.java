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

import it.bz.prov.controlli.bo.ControlloreBO;
import it.bz.prov.controlli.test.config.EndpointConfig;

public class TestGestioneControlloriEndpoint {
	
	/****************************************************************************/
	/*				GESTIONE ANAGRAFICA CONTROLLORE								*/
	/****************************************************************************/
	
	
	@Test
	public void insertControlloreTest() {
		URL url;
		try {
			ControlloreBO c = new ControlloreBO();
			c.set_username("bpettazzoni");
			c.set_nome("Barbara");
			c.set_cognome("Pettazzoni");
							
			url = new URL(EndpointConfig.GESTIONE_CONTROLLORI_ENDPOINT + "/insertContr");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("POST");
			ObjectMapper mapper = new ObjectMapper();
			String str =  mapper.writeValueAsString(c);
			byte[] outputInBytes = str.getBytes("UTF-8");
			OutputStream os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			System.out.println("insertControlloreTest: " + httpCon.getResponseMessage());
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
	public void modifyControlloreTest() {
		URL url;
		try {
			System.out.println("******* modify *******************");
			System.out.println("---- insert");
			
			ControlloreBO c = new ControlloreBO();
			c.set_username("rcaliandro");
			c.set_nome("Rocco");
			c.set_cognome("Caliandro");
						
			url = new URL(EndpointConfig.GESTIONE_CONTROLLORI_ENDPOINT + "/insertContr");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("POST");
			ObjectMapper mapper = new ObjectMapper();
			String str =  mapper.writeValueAsString(c);
			byte[] outputInBytes = str.getBytes("UTF-8");
			OutputStream os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			System.out.println("insert: esito - " + httpCon.getResponseMessage());
			
			System.out.println("---- modify");
			
			c.set_nome("Rocco_");
			c.set_cognome("Caliandro_");
			 
			url = new URL(EndpointConfig.GESTIONE_CONTROLLORI_ENDPOINT + "/modifyContr");
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("PUT");
			mapper = new ObjectMapper();
			 str =  mapper.writeValueAsString(c);
			outputInBytes = str.getBytes("UTF-8");
			os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();
			
			System.out.println("modifyControlloreTest: " + httpCon.getResponseMessage());
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
	
	
	
	@Test
	public void deleteControlloreTest() {
		URL url;
		try {
			System.out.println("******* delete *******************");
			System.out.println("---- insert");
			ControlloreBO c = new ControlloreBO();
			c.set_username("mrighi");
			c.set_nome("Massimo");
			c.set_cognome("Righi");
			
			url = new URL(EndpointConfig.GESTIONE_CONTROLLORI_ENDPOINT + "/insertContr");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestMethod("POST");
			ObjectMapper mapper = new ObjectMapper();
			String str =  mapper.writeValueAsString(c);
			byte[] outputInBytes = str.getBytes("UTF-8");
			OutputStream os = httpCon.getOutputStream();
			os.write( outputInBytes );    
			os.close();

			System.out.println("insert: esito - " + httpCon.getResponseMessage());
			System.out.println("---- delete");
				
			url = new URL(EndpointConfig.GESTIONE_CONTROLLORI_ENDPOINT + "/delete");
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("DELETE");
			httpCon.setDoOutput(true);
			httpCon.setRequestProperty("Content-Type", "application/json");
			mapper = new ObjectMapper();
			str =  mapper.writeValueAsString(c);
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
	
	
	
	@Test
	public void readControlloriTest() {
		URL url;
		try {
			
			url = new URL(EndpointConfig.GESTIONE_CONTROLLORI_ENDPOINT + "/getListContr");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("GET");

			System.out.println("readControlloriTest: " + httpCon.getResponseMessage());
			if (httpCon.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output = "";
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				List<ControlloreBO> list = Arrays.asList(new ObjectMapper().readValue(sb.toString(), ControlloreBO[].class));
				

				System.out.println("***** controllori NEL DB *****");
				for(ControlloreBO c: list) {
					System.out.println("____________________________");
					System.out.println(c.toString());
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
