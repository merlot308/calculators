package com.ecsdepot.investing.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ecsdepot.investing.utilities.InvestingProperties;

public class MarkitDataSource
{
	private List<CompanyDO> companies = new ArrayList<CompanyDO>();

	public static void main(String[] args)
	{
		InvestingProperties props = new InvestingProperties("investing.properties");

		System.out.println("calc:" + props.getQuotesURI());
		System.out.println("lookup: " + props.getLookupURI());

		StringBuilder quoteURIBuilder = new StringBuilder(props.getQuotesURI());
		// quoteURIBuilder.append(InvestConstants.SYMBOL).append(InvestConstants.EQUALS).append("AAPL");

		System.out.println(quoteURIBuilder.toString());

		try
		{
			// StringBuilder temp2 = getURLResponse(quoteURIBuilder);

			// List<TickerList> companieslist =
			// dbcon.getKnownTickers(dbcon.getEntityManager());
			List<String> companieslist = getCompanyTickers();
			List<CompanyDO> companiesList = new ArrayList<>();
			if (companieslist.isEmpty())
			{
				System.out.println("LIST IS EMPTY!!!!!! ");
			} else
			{
				String request = null;
				int counter = 0;
				String response = null;
				for (String t : companieslist)
				{
					StringBuilder quoteURIBuilder2 = new StringBuilder(props.getQuotesURI());
					quoteURIBuilder2.append(InvestConstants.SYMBOL).append(InvestConstants.EQUALS).append(t);

					if (counter == 15)
					{
						request = quoteURIBuilder2.toString();
						System.out.println("REQ" + request);
						response = getURLResponse(request);
						CompanyDO company = createCompanyInfoDO(response);
						companiesList.add(company);
					}

					counter++;

				}
			}
			System.out.println("size: " + companiesList.size());
			System.out.println("Company: " + companiesList.get(0).getCompanyName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private static List<String> getCompanyTickers()
	{
		List<String> companiesList = new ArrayList<String>();
		companiesList.add("AAC");
		companiesList.add("AAU");
		companiesList.add("ABL");
		companiesList.add("ACU");
		companiesList.add("ACY");
		companiesList.add("ADGI");
		companiesList.add("ADK");
		companiesList.add("AE");
		companiesList.add("AEN");
		companiesList.add("AEZ");
		companiesList.add("AFT");
		companiesList.add("AGT");
		companiesList.add("AGX");
		companiesList.add("AIM");
		companiesList.add("HAIPF");
		companiesList.add("AIS");
		companiesList.add("AMS");
		companiesList.add("ANO");
		companiesList.add("ANV");
		companiesList.add("MSTX");
		companiesList.add("API");
		companiesList.add("APO");
		companiesList.add("APP");
		companiesList.add("APT");
		companiesList.add("APYI");
		companiesList.add("AQQ");
		companiesList.add("ATCV");
		companiesList.add("AWX");
		companiesList.add("AXK");
		companiesList.add("AXU");
		companiesList.add("AZC");
		companiesList.add("AZK");
		companiesList.add("BAA");
		companiesList.add("BBH");
		companiesList.add("BCV");
		companiesList.add("BDH");
		companiesList.add("BDL");
		companiesList.add("BDR");
		companiesList.add("BFY");
		companiesList.add("BHB");
		companiesList.add("BHH");
		companiesList.add("BHO");
		companiesList.add("BHV");
		companiesList.add("BKJ");
		companiesList.add("BKR");
		companiesList.add("BLD");
		companiesList.add("BLE");
		companiesList.add("BLJ");
		companiesList.add("BMJ");
		companiesList.add("BNX");
		companiesList.add("BPS");
		companiesList.add("BPZ");
		companiesList.add("BQI");
		companiesList.add("BQY");
		companiesList.add("BRN");
		companiesList.add("BTC");
		companiesList.add("BTI");
		companiesList.add("BTN");
		companiesList.add("BVX");
		companiesList.add("BWL.A");
		companiesList.add("BZC");
		companiesList.add("BZM");
		companiesList.add("CAW");
		companiesList.add("CCA");
		companiesList.add("CCF");
		companiesList.add("CDY");
		companiesList.add("CEF");
		companiesList.add("CET");
		companiesList.add("CEV");
		companiesList.add("CFK");
		companiesList.add("CFP");
		companiesList.add("CFW");
		companiesList.add("CGA");
		companiesList.add("CGL.A");
		companiesList.add("CGR");
		companiesList.add("CH");
		companiesList.add("CIK");
		companiesList.add("CKX");
		companiesList.add("CLM");
		companiesList.add("CMT");
		companiesList.add("CNR");
		companiesList.add("CNU");
		companiesList.add("COR");
		companiesList.add("CQP");
		companiesList.add("CRC");
		companiesList.add("CRF");
		companiesList.add("CRV");
		companiesList.add("CTO");
		companiesList.add("CUO");
		companiesList.add("CUR");
		companiesList.add("CVF");
		companiesList.add("CVM");
		companiesList.add("CVR");
		companiesList.add("CVU");
		companiesList.add("CXM");
		companiesList.add("CXZ");
		companiesList.add("DDD");
		companiesList.add("DFR");
		companiesList.add("DHY");
		companiesList.add("DINI");
		companiesList.add("DIT");
		companiesList.add("DLA");
		companiesList.add("DSS");
		companiesList.add("DMF");
		companiesList.add("DUNR");
		companiesList.add("DNN");
		companiesList.add("DPW");
		companiesList.add("DRJ");
		companiesList.add("DXR");
		companiesList.add("EAD");
		companiesList.add("EAG");
		companiesList.add("EAR");
		companiesList.add("ECF");
		companiesList.add("EGI");
		companiesList.add("EGO");
		companiesList.add("EGT");
		companiesList.add("EGX");
		companiesList.add("EIA");
		companiesList.add("EIM");
		companiesList.add("EIO");
		companiesList.add("EIP");
		companiesList.add("EIV");
		companiesList.add("EKH");
		companiesList.add("ELC");
		companiesList.add("ELTP");
		companiesList.add("EMI");
		companiesList.add("EMJ");
		companiesList.add("EML");
		companiesList.add("ENA");
		companiesList.add("END");
		companiesList.add("ENX");
		companiesList.add("EPM");
		companiesList.add("ERC");
		companiesList.add("ERH");
		companiesList.add("ESA");
		companiesList.add("ESP");
		companiesList.add("ETCC");
		companiesList.add("ETF");
		companiesList.add("EVJ");
		companiesList.add("EVK");
		companiesList.add("EVM");
		companiesList.add("EVO");
		companiesList.add("EVP");
		companiesList.add("EVV");
		companiesList.add("EVY");
		companiesList.add("EXK");
		companiesList.add("FAX");
		companiesList.add("FCO");
		companiesList.add("FEN");
		companiesList.add("FEP");
		companiesList.add("FFI");
		companiesList.add("FHC");
		companiesList.add("FLL");
		companiesList.add("FOH");
		companiesList.add("FPP");
		companiesList.add("FRD");
		companiesList.add("FRS");
		companiesList.add("FSI");
		companiesList.add("FSP");
		companiesList.add("FTF");
		companiesList.add("FVE");
		companiesList.add("FWV");
		companiesList.add("CUSI");
		companiesList.add("GANS");
		companiesList.add("GBGLF");
		companiesList.add("GRBU");
		companiesList.add("GBR");
		companiesList.add("GNTP");
		companiesList.add("GEL");
		companiesList.add("GGN");
		companiesList.add("GGR");
		companiesList.add("GHM");
		companiesList.add("GIGIQ");
		companiesList.add("GIW");
		companiesList.add("GLA");
		companiesList.add("GLO");
		companiesList.add("GLQ");
		companiesList.add("GLU");
		companiesList.add("GLV");
		companiesList.add("GMO");
		companiesList.add("GOK");
		companiesList.add("GPR");
		companiesList.add("GRC");
		companiesList.add("GRF");
		companiesList.add("GRH");
		companiesList.add("GRZ");
		companiesList.add("GSB");
		companiesList.add("GSS");
		companiesList.add("GST");
		companiesList.add("GSX");
		companiesList.add("GTE");
		companiesList.add("CMXI");
		companiesList.add("GTU");
		companiesList.add("GV");
		companiesList.add("GVP");
		companiesList.add("HDY");
		companiesList.add("HEB");
		companiesList.add("HEM");
		companiesList.add("HH");
		companiesList.add("HHH");
		companiesList.add("HKN");
		companiesList.add("HMG");
		companiesList.add("HNB");
		companiesList.add("HNW");
		companiesList.add("HOL");
		companiesList.add("HPJ");
		companiesList.add("HQSM");
		companiesList.add("HRT");
		companiesList.add("HUTC");
		companiesList.add("HTM");
		companiesList.add("HWG");
		companiesList.add("HWK");
		companiesList.add("IAF");
		companiesList.add("IAH");
		companiesList.add("IAGI");
		companiesList.add("ICH");
		companiesList.add("COGT");
		companiesList.add("IDN");
		companiesList.add("IF");
		companiesList.add("IG");
		companiesList.add("IGC");
		companiesList.add("IHT");
		companiesList.add("EXE");
		companiesList.add("IIH");
		companiesList.add("ILIU");
		companiesList.add("ILXRQ");
		companiesList.add("IMMP");
		companiesList.add("IMO");
		companiesList.add("IMSC");
		companiesList.add("INO");
		companiesList.add("INS");
		companiesList.add("IOC");
		companiesList.add("IOT");
		companiesList.add("IPB");
		companiesList.add("IPT");
		companiesList.add("ISL");
		companiesList.add("ISR");
		companiesList.add("INSV");
		companiesList.add("ITI");
		companiesList.add("IVD");
		companiesList.add("JENNQ");
		companiesList.add("JLIC");
		companiesList.add("JOB");
		companiesList.add("JRS");
		companiesList.add("JVA");
		companiesList.add("KAD");
		companiesList.add("KAZ");
		companiesList.add("KBX");
		companiesList.add("KGN");
		companiesList.add("KOG");
		companiesList.add("KRU");
		companiesList.add("CRYXF");
		companiesList.add("KUN");
		companiesList.add("LAQ");
		companiesList.add("LCI");
		companiesList.add("CEI");
		companiesList.add("LGL");
		companiesList.add("LKII");
		companiesList.add("LNG");
		companiesList.add("LOV");
		companiesList.add("LTS");
		companiesList.add("MAB");
		companiesList.add("MBA");
		companiesList.add("MBR");
		companiesList.add("MCF");
		companiesList.add("MCZ");
		companiesList.add("MDF");
		companiesList.add("MDM");
		companiesList.add("MDW");
		companiesList.add("MEA");
		companiesList.add("MFN");
		companiesList.add("MGH");
		companiesList.add("MGN");
		companiesList.add("MGT");
		companiesList.add("MHE");
		companiesList.add("MHH");
		companiesList.add("MSGNF");
		companiesList.add("MIW");
		companiesList.add("MKH");
		companiesList.add("MMV");
		companiesList.add("MOC");
		companiesList.add("MSL");
		companiesList.add("MSN");
		companiesList.add("MVF");
		companiesList.add("MVG");
		companiesList.add("MXA");
		companiesList.add("MXC");
		companiesList.add("MXN");
		companiesList.add("MZA");
		companiesList.add("NAK");
		companiesList.add("NAQ");
		companiesList.add("NBFAQ");
		companiesList.add("NBH");
		companiesList.add("NBJ");
		companiesList.add("NBO");
		companiesList.add("CLBS");
		companiesList.add("NBW");
		companiesList.add("NBY");
		companiesList.add("NCU");
		companiesList.add("NEA");
		companiesList.add("NEN");
		companiesList.add("NFC");
		companiesList.add("NFM");
		companiesList.add("NFZ");
		companiesList.add("NG");
		companiesList.add("NGB");
		companiesList.add("NGD");
		companiesList.add("NGK");
		companiesList.add("NGO");
		companiesList.add("NGX");
		companiesList.add("NHC");
		companiesList.add("NII");
		companiesList.add("NIVS");
		companiesList.add("NKG");
		companiesList.add("NKL");
		companiesList.add("NKO");
		companiesList.add("NKR");
		companiesList.add("NKX");
		companiesList.add("NLP");
		companiesList.add("NMB");
		companiesList.add("NMZ");
		companiesList.add("NNB");
		companiesList.add("NNO");
		companiesList.add("NOG");
		companiesList.add("NOM");
		companiesList.add("NPG");
		companiesList.add("NRB");
		companiesList.add("NRK");
		companiesList.add("NRO");
		companiesList.add("NSU");
		companiesList.add("NTN");
		companiesList.add("NUJ");
		companiesList.add("NVDL");
		companiesList.add("NVG");
		companiesList.add("NVJ");
		companiesList.add("NVX");
		companiesList.add("NVY");
		companiesList.add("NDAC");
		companiesList.add("NWI");
		companiesList.add("NXE");
		companiesList.add("NXG");
		companiesList.add("NXI");
		companiesList.add("NXJ");
		companiesList.add("NXK");
		companiesList.add("NXM");
		companiesList.add("NXZ");
		companiesList.add("NYH");
		companiesList.add("NZF");
		companiesList.add("NZH");
		companiesList.add("NZR");
		companiesList.add("NZW");
		companiesList.add("NZX");
		companiesList.add("OFI");
		companiesList.add("OIH");
		companiesList.add("OPK");
		companiesList.add("ORSX");
		companiesList.add("PAL");
		companiesList.add("PBM");
		companiesList.add("PCC");
		companiesList.add("PCE");
		companiesList.add("PDC");
		companiesList.add("PDO");
		companiesList.add("VPGI");
		companiesList.add("PHC");
		companiesList.add("PHF");
		companiesList.add("PIP");
		companiesList.add("PKT");
		companiesList.add("PLG");
		companiesList.add("PLM");
		companiesList.add("PLX");
		companiesList.add("PFRMF");
		companiesList.add("PNS");
		companiesList.add("PPH");
		companiesList.add("PRK");
		companiesList.add("PTN");
		companiesList.add("PW");
		companiesList.add("PXFG");
		companiesList.add("PZG");
		companiesList.add("QBC");
		companiesList.add("QNGPE");
		companiesList.add("QMM");
		companiesList.add("RAP");
		companiesList.add("RBY");
		companiesList.add("RCG");
		companiesList.add("RDI");
		companiesList.add("RFA");
		companiesList.add("RGRX");
		companiesList.add("RIC");
		companiesList.add("RVHLQ");
		companiesList.add("RKH");
		companiesList.add("RNJ");
		companiesList.add("RNN");
		companiesList.add("RNY");
		companiesList.add("ROX");
		companiesList.add("RPI");
		companiesList.add("RTH");
		companiesList.add("RTK");
		companiesList.add("RVP");
		companiesList.add("RVR");
		companiesList.add("RWC");
		companiesList.add("SA");
		companiesList.add("SAL");
		companiesList.add("SBI");
		companiesList.add("SEB");
		companiesList.add("SGA");
		companiesList.add("SGB");
		companiesList.add("SHZ");
		companiesList.add("SIF");
		companiesList.add("SIM");
		companiesList.add("SLI");
		companiesList.add("SMDM");
		companiesList.add("SMH");
		companiesList.add("SNT");
		companiesList.add("SSE");
		companiesList.add("SSN");
		companiesList.add("SSY");
		companiesList.add("STS");
		companiesList.add("SLPH");
		companiesList.add("SVA");
		companiesList.add("SVM");
		companiesList.add("SVT");
		companiesList.add("SWH");
		companiesList.add("TA");
		companiesList.add("TBV");
		companiesList.add("TCX");
		companiesList.add("TF");
		companiesList.add("TGB");
		companiesList.add("TGC");
		companiesList.add("THM");
		companiesList.add("TIK");
		companiesList.add("TIS");
		companiesList.add("TIV");
		companiesList.add("TKOI");
		companiesList.add("TLF");
		companiesList.add("TLR");
		companiesList.add("TNLX");
		companiesList.add("KRNT");
		companiesList.add("TMP");
		companiesList.add("TOF");
		companiesList.add("TPI");
		companiesList.add("TRX");
		companiesList.add("TRT");
		companiesList.add("SPCO");
		companiesList.add("TSH");
		companiesList.add("TTH");
		companiesList.add("ATYM");
		companiesList.add("UDWK");
		companiesList.add("UEC");
		companiesList.add("UG");
		companiesList.add("ULU");
		companiesList.add("UMH");
		companiesList.add("UPG");
		companiesList.add("UPI");
		companiesList.add("UQM");
		companiesList.add("URG");
		companiesList.add("URZ");
		companiesList.add("UTA");
		companiesList.add("UTG");
		companiesList.add("UTH");
		companiesList.add("UUU");
		companiesList.add("UVE");
		companiesList.add("UWN");
		companiesList.add("VCF");
		companiesList.add("VFL");
		companiesList.add("VGZ");
		companiesList.add("VHC");
		companiesList.add("VII");
		companiesList.add("VKI");
		companiesList.add("VKL");
		companiesList.add("VMM");
		companiesList.add("VMV");
		companiesList.add("VSR");
		companiesList.add("VTG");
		companiesList.add("WEL");
		companiesList.add("WEX");
		companiesList.add("WGA");
		companiesList.add("WGW");
		companiesList.add("WLB");
		companiesList.add("WMH");
		companiesList.add("WLSE");
		companiesList.add("WTT");
		companiesList.add("WYY");
		companiesList.add("FU");
		companiesList.add("NTS");
		companiesList.add("XNNH");
		companiesList.add("XPL");
		companiesList.add("XPO");
		companiesList.add("XRA");
		companiesList.add("YMI");
		companiesList.add("ESNC");
		companiesList.add("ZN");
		companiesList.add("6A_F");
		companiesList.add("6B_F");
		companiesList.add("6C_F");
		companiesList.add("6E_F");
		companiesList.add("6J_F");
		companiesList.add("6M_F");
		companiesList.add("6N_F");
		companiesList.add("6S_F");
		companiesList.add("AL_F");
		companiesList.add("CL_F");
		companiesList.add("DJ_F");
		companiesList.add("DX_F");
		companiesList.add("EMD_F");
		companiesList.add("ES_F");
		companiesList.add("GC_F");
		companiesList.add("GE_F");
		companiesList.add("HG_F");
		companiesList.add("ND_F");
		companiesList.add("NG_F");
		companiesList.add("NQ_F");
		companiesList.add("PL_F");
		companiesList.add("SI_F");
		companiesList.add("SP_F");
		companiesList.add("TF_F");
		companiesList.add("YM_F");
		companiesList.add("ZB_F");
		companiesList.add("ZN_F");
		companiesList.add("AUDCAD");
		companiesList.add("AUDJPY");
		companiesList.add("AUDNZD");
		companiesList.add("AUDUSD");
		companiesList.add("EURGBP");
		companiesList.add("EURJPY");
		companiesList.add("EURUSD");
		companiesList.add("GBPJPY");
		companiesList.add("GBPNZD");
		companiesList.add("GBPUSD");
		companiesList.add("NZDUSD");
		companiesList.add("USDCAD");
		companiesList.add("USDCHF");
		companiesList.add("USDJPY");
		companiesList.add("USDMXN");
		companiesList.add("COMPQ");
		companiesList.add("DJIA");
		companiesList.add("SPX");
		companiesList.add("VIX");
		companiesList.add("AACC");
		companiesList.add("AAIIQ");
		companiesList.add("AAME");
		companiesList.add("AAON");
		companiesList.add("AAPL");
		companiesList.add("AATI");
		companiesList.add("AAUKF");
		companiesList.add("AAWW");
		companiesList.add("AAXJ");
		companiesList.add("ABAT");
		companiesList.add("ABAX");
		companiesList.add("ABBC");
		companiesList.add("ABCB");
		companiesList.add("ABCO");
		companiesList.add("ABCW");
		companiesList.add("ARCB");
		companiesList.add("ABII");
		companiesList.add("ABIO");
		companiesList.add("ABMC");
		companiesList.add("ABMD");
		companiesList.add("ABPI");

		return companiesList;
	}

	/**
	 * @param jsonString
	 * @param fieldNames
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws JsonParseException
	 */
	private static CompanyDO createCompanyInfoDO(String jsonString)
			throws ParseException, JsonParseException, IOException
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonString);
		JSONObject jobj = (JSONObject) obj;
		CompanyDO company = new CompanyDO();
		List<String> fieldNames = getFieldNamesList(jsonString);
		for (String s : fieldNames)
		{
			if (InvestConstants.NAME.equalsIgnoreCase(s))
			{
				// System.out.println(jobj.get(s).toString());
				company.setCompanyName(jobj.get(s).toString());
			}
			if (InvestConstants.SYMBOL.equalsIgnoreCase(s))
			{
				company.setTicker(jobj.get(s).toString());
				// System.out.println(company.getTicker());
			}
			if (InvestConstants.LAST_PRICE.equalsIgnoreCase(s))
			{
				// create way to set current price
			}
			if (InvestConstants.CHANGE.equalsIgnoreCase(s))
			{
				// create way to set the change
			}
			if (InvestConstants.CHANGE_PERCENT.equalsIgnoreCase(s))
			{
				// create way to set
			}
			if (InvestConstants.MSDATE.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.MARKET_CAP.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.VOLUME.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.CHANGE_YTD.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.CHANGE_PERCENT_YTD.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.HIGH.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.LOW.equalsIgnoreCase(s))
			{
				// set
			}
			if (InvestConstants.OPEN.equalsIgnoreCase(s))
			{
				// set
			}
		}
		return company;
	}

	/**
	 * @param quoteURIBuilder
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private static String getURLResponse(String quoteURI) throws MalformedURLException, IOException
	{
		System.out.println("URL QUOTE: " + quoteURI);
		URL url = new URL(quoteURI);

		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

		String temp = new String();
		StringBuilder temp2 = new StringBuilder();
		while (null != (temp = br.readLine()))
		{
			temp2.append(temp);
		}
		return temp2.toString();
	}

	/**
	 * @param string
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 */
	private static List<String> getFieldNamesList(String string) throws IOException, JsonParseException
	{
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser jsonParser = jsonFactory.createJsonParser(string);
		List<String> fieldNames = new ArrayList<String>();
		while (!jsonParser.isClosed())
		{
			JsonToken jsonToken = jsonParser.nextToken();
			if (JsonToken.FIELD_NAME.equals(jsonToken))
			{
				String fieldName = jsonParser.getCurrentName();
				fieldNames.add(fieldName);
			}
		}
		return fieldNames;
	}

}
