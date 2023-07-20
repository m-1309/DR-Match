package health_filter;
import ischemic_filter.LinkedList;
import java.io.*;
import java.nio.file.Files;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import Notification.*;
public class med_hist {
	LinkedList o3;
	String desc;
	public med_hist(LinkedList o4){
		o3=o4;
		System.out.println("o3=");
		o3.display();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();    
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
			LinkedList ptr = o3.front;
			while(ptr!=null)
			{
				String query = "select Medical_History from recipient where Recipient_Id= "+ptr.info;
				Statement sta = connection.createStatement();
				ResultSet rset = sta.executeQuery(query);
				while(rset.next()){
					desc=rset.getString("Medical_History");
					ArrayList<String> str1 =keywords(desc);
					int x=check(str1);
					if(x==1)
						o3.delete_v(ptr.info);
					
				}
				ptr=ptr.link;
			}
			
			}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public ArrayList<String> keywords(String des) {
		String str=des;

        // System.out.println(str);
        String stt;
        StringTokenizer st = new StringTokenizer(str, " ");
        //System.out.println(st.countTokens());
        Vector<String> v = new Vector<String>();
        Vector<String> v1 = new Vector<String>();
        while (st.hasMoreTokens()) {
        	stt=st.nextToken();
        	stt.toLowerCase();
        
        	Collections.addAll(v,stt);
        }
        LinkedHashSet<String> hashSet = new LinkedHashSet<String>(v); //to remove duplicates
        v1.addAll(hashSet);
        ArrayList<String> Noduplic = new ArrayList<String>();
        for (int i = 0; i < v1.size(); i++)
            Noduplic.add(v1.get(i));
        List<String> stopwords = Arrays.asList("a","abaft","abafter","abaftest","able","about","abouter","aboutest","above","above-","abover","abovest","accordance","according","accordingly","across","aer","aest","afore","after","afterer","afterest","afterward","afterwards","again","against","age","aid","ain","albeit","all","aller","allest","alls","allyou","almost","along","alongside","already","also","alternatively","although","always","am","amid","amidst","among","amongst","an","and","and","or","andor","anear","anent","another","any","anybody","anyhow","anyone","anything","anywhere","apart","aparter","apartest","appear","appeared","appearing","appears","appropriate","appropriated","appropriater","appropriates","appropriatest","appropriating","are","aren","aren’t","ares","around","as","ases","aside","asides","asking","aslant","astraddle","astraddler","astraddlest","astride","astrider","astridest","at","athwart","atop","atween","aught","aughts","available","availabler","availablest","awfully","b","be","became","because","become","becomes","becoming","becominger","becomingest","becomings","been","before","beforehand","beforehander","beforehandest","behind","behinds","being","believe","below","beneath","beside","besides","better","bettered","bettering","betters","between","betwixt","beyond","bist","both","but","buts","by","byandby","by-and-by","c","called","can","cannot","canst","cant","canted","cantest","canting","cants","care","cer","certain","certainer","certainest","cest","chez","circa","claim","clinical","co","com","come","comeon","come-on","comeons","come-ons","comprises","concerning","concerninger","concerningest","condition","consequently","considering","consistent","cont","corresponding","could","couldn","couldn’t","couldst","cum","d","daughter","dday","ddays","describe","described","describes","describing","desired","despite","despited","despites","despiting","details","developing","did","didn","didn’t","didnt","different","differenter","differentest","disclosure","do","doe","does","doesn","doesn’t","doesnt","doing","doings","don","don’t","done","doner","dones","donest","dos","dost","doth","down","downs","downward","downwarder","downwardest","downwards","due","during","e","e.g","each","earlier","easily","easy","eg","eight","either","else","elsewhere","embodiment","enough","ere","especially","essentially","et","etc","even","evened","evenest","evens","evenser","evensest","eventually","ever","every","everybody","everyone","everything","everywhere","ex","excellent","except","excepted","excepting","excepts","excluded","exes","f","fact","facts","failing","failings","fairly","family","father","few","fewer","fewest","fig","figs","figupon","figuponed","figuponing","figupons","finally","five","follow","followthrough","for","forby","forbye","fore","forer","fores","forever","former","formerer","formerest","formerly","formers","fornenst","forward","forwhy","four","fourscore","frae","from","fs","further","furthered","furtherer","furtherest","furthering","furthermore","furthers","g","generally","get","gets","getting","give","giving","go","goes","gone","good","got","gotta","gotten","group","h","had","hadn","hadn’t","hadst","hae","hardly","has","hasn","hasn’t","hast","hath","have","haven","haven’t","haves","having","he","he","she","hence","her","here","hereafter","hereafters","hereby","herein","hereupon","hers","herself","him","him","her","himself","his","his","her","hither","hitherer","hitherest","hoo","hoos","how","howbeit","howdoyoudo","how-do-you-do","however","huh","humph","husband","i","idem","idemer","idemest","ie","if","ifs","ii","iii","immediate","immediately","immediater","immediatest","in","inasmuch","inc","including","indeed","indicate","indicated","indicates","indicating","info","information","insofar","instead","into","invention","inward","inwarder","inwardest","inwards","is","isn","isn’t","it","it’s","its","itself","j","just","k","l","later","latter","latterer","latterest","latterly","latters","layabout","layabouts","less","lest","like","liked","likely","little","ll","lot","lots","lotted","lotting","m","ma","main","make","many","mauger","maugre","may","mayest","me","means","meanwhile","meanwhiles","medical","mentioned","midst","midsts","might","mightn","mightn’t","mights","more","moreover","most","mostly","mother","much","mucher","muchest","must","musth","musths","mustn","mustn’t","musts","my","myself","n","natheless","nathless","neath","neaths","necessarier","necessariest","necessary","needed","needn","needn’t","neither","nethe","nethermost","never","nevertheless","new","nigh","nigher","nighest","nine","no","nobodies","nobody","noes","non","none","noone","no-one","nor","nos","not","nothing","nothings","noticed","notwithstanding","now","nowhere","nowheres","o","of","off","offest","offs","often","oftener","oftenest","oh","ok","old","on","once","one","oneself","onest","only","ons","onto","or","orer","orest","other","others","otherwise","otherwiser","otherwisest","ought","oughts","our","ours","ourself","ourselves","out","outed","outest","outs","outside","outwith","over","overall","overaller","overallest","overalls","overs","own","owned","owning","owns","owt","p","particular","particularer","particularest","particularly","particulars","patient","per","perhaps","plaintiff","please","pleased","pleases","plenties","plenty","possible","preferably","preferred","present","presumed","pro","probably","provide","provided","provides","providing","pt","q","qua","que","quite","r","rath","rathe","rather","rathest","re","really","received","regarding","relate","related","relatively","remarkably","requested","require","required","res","respecting","respectively","s","said","saider","saidest","same","samer","sames","samest","sans","sanserif","sanserifs","sanses","saved","sayid","sayyid","seem","seemed","seeminger","seemingest","seemings","seems","send","sent","senza","serious","seriouser","seriousest","seven","several","severaler","severalest","shall","shalled","shalling","shalls","shan","shan’t","she","she’s","should","should’ve","shoulded","shoulding","shouldn","shouldn’t","shoulds","significantly","simply","since","sine","sines","sith","six","so","sobeit","soer","soest","some","somebody","somehow","someone","something","sometime","sometimer","sometimes","sometimest","somewhat","somewhere","son","specifically","still","stop","stopped","straight","substantially","such","suggest","suitable","summat","sup","supped","supping","sups","syn","syne","t","ten","than","that","that’ll","thats","the","thee","their","theirs","them","themselves","then","thence","thener","thenest","there","thereafter","therebetween","thereby","therefor","therefore","therefrom","therein","thereinto","thereof","thereon","therer","therest","therethrough","thereto","thereupon","therewith","these","they","thine","thing","things","this","thises","thorough","thorougher","thoroughest","thoroughly","those","thou","though","thous","thouses","three","thro","through","througher","throughest","throughout","thru","thruer","thruest","thus","thy","thyself","till","tilled","tilling","tills","to","together","too","took","total","toward","towarder","towardest","towards","two","typical","u","umpteen","under","underlying","underneath","unless","unlike","unlikely","unliker","unlikest","until","unto","up","upon","uponed","uponing","upons","upped","upping","ups","us","use","used","usedest","useful","username","usually","v","various","variouser","variousest","ve","verier","veriest","versus","very","via","vice","vis-a-vis","vis-a-viser","vis-a-visest","viz","vs","w","want","wants","was","wasn","wasn’t","wasnt","wast","we","well","were","weren","weren’t","wert","what","whatever","whateverer","whateverest","whatsoever","whatsoeverer","whatsoeverest","wheen","when","whenas","whence","whencesoever","whenever","whensoever","where","whereafter","whereas","whereat","whereby","wherefrom","wherein","whereinto","whereof","whereon","wheresoever","whereto","whereupon","wherever","wherewith","wherewithal","whether","which","whichever","whichsoever","while","whiles","whilst","whither","whithersoever","who","whoever","whom","whomever","whose","whoso","whosoever","why","will","with","withal","within","without","won","won’t","work","would","woulded","woulding","wouldn","wouldn’t","woulds","www","x","y","ye","yet","yon","yond","yonder","you","you’d","you’ll","you’re","you’ve","your","yours","yourself","yourselves","z","zillion","least","nearly","actually","approximately","basically","frequently","occasionally","seldom","rarely","gradually","recently","previously","simultaneously","subsequently","ultimately","likewise","nonetheless","similarly");
        Noduplic.removeAll(stopwords);
        
        return Noduplic;
	}
	public int check(ArrayList<String> s) {
		List<String> checkWords=Arrays.asList("abscessed lymph node","absolute neutrophil count","accumulation of fluid in the abdomen","acquired immunodeficiency syndrome","active bleeding disorders","active hepatitis b infection","active hepatitis c infection","active infections or sepsis","active suicidal ideation","active tuberculosis","acute hepatitis b","acute kidney injury","acute respiratory distress syndrome","addiction","adenocarcinoma","adverse drug reactions","aggression","aids","aids-related cancers","airway hyperresponsiveness","airway inflammation","alcohol abuse","alcoholic","alcoholic hepatitis","alcoholic liver disease","allergic asthma","alzheimer's disease","amoebiasis","amoebic liver abscess","anaphylaxis","anc","anemia","angina pectoris","antiarrhythmics","antibiotic resistance","antibiotic stewardship","anticoagulants","anticonvulsants","antidiabetic medication","antiplatelet medication","antitubercular therapy","aplastic anemia","ards","ascites","asthma exacerbation","asymptomatic bacteriuria","atherosclerosis","atopic dermatitis","atypical mycobacterial infection","auditory hallucinations","autoimmune disorders that affect blood vessels or connective tissue","autoimmune hepatitis","autoimmune liver disease","autoimmunity","azathioprine","barrel chest","bile duct damage","bipolar disorder","bladder infection","bleeding disorder or abnormal blood clotting","blood clot in a vein such as dvt","blood clots","bloodstream infection","blue bloaters","brain abscess","brief psychotic disorder","bronchiectasis","bronchopulmonary","buerger's disease","calcineurin inhibitors","calcium channel blockers","cancer","cancer recurrence","cancer treatment","candidiasis","cap","carbapenem-resistant enterobacteriaceae","carcinoma","catheter-associated uti","cd4 count","cellulitis","central nervous system infection","chagas disease","chemotherapy","chemotherapy-induced neutropenia","chronic airflow obstruction","chronic bronchitis","chronic hepatitis","chronic hepatitis b","chronic infections","chronic kidney disease","chronic liver disease","chronic mucus hypersecretion","chronic obstructive pulmonary disease","chronic respiratory failure","chronic viral hepatitis","cirrhosis","clostridium difficile infection","cmv","coagulopathy","cocaine abuse","colitis","complement activation","congenital neutropenia","continuous positive airway pressure","copd","copper accumulation","corticosteroids","cpap","cre","critical limb ischemia","cross-reactivity","cryptococcal meningitis","cryptosporidiosis","cyclosporine","cyclothymia","cystic fibrosis","cystitis","cytochrome p450","cytomegalovirus","death of tissue due to lack of blood flow or infection","deep vein thrombosis","delusional disorder","delusions of control","dementia","diabetes","diabetes mellitus","diabetic complications","diabetic foot","diabetic foot cellulitis","diabetic foot ulcers","diabetic nephropathy","diabetic neuropathy","diabetic retinopathy","diaebtic","dialysis","dic","disseminated histoplasmosis","disseminated infection","disseminated intravascular coagulation","disseminated strongyloidiasis","drug abuse","drug-induced liver injury","drug-resistant tuberculosis","dvt","dyslipidemia","dyspnea on exertion","eczema","eisenmenger syndrome","embryo-fetal development","emphysema","encephalitis","encephalitis retinitis","encephalopathy","end-stage renal disease","endocarditis","endothelin receptor antagonists","endotoxemia","entamoeba histolytica","eosinophilic pneumonia","eosinophils","epidural abscess","epinephrine","erotomanic delusions","erysipelas","erythema multiforme","esbl","essential hypertension","experimental cancer treatments","extended-spectrum beta-lactamase","extensively drug-resistant tuberculosis","exudate","fatty liver","febrile neutropenia","fetal toxicity","fixed drug eruption","fluid and electrolyte imbalance","fungal infection","gangrene","gas gangrene","genetic disorder","giardiasis","glomerulonephritis","glucose intolerance","graft-versus-host disease","granulomas","granulomatous disease","gvhd","hallucinations","hap","hbv","hcv","hematuria","hemochromatosis","hemolytic anemia","hemolytic-uremic syndrome","hemoptysis","heparin-induced thrombocytopenia","hepatic encephalopathy","hepatitis","hepatitis b","hepatocellular carcinoma","herpes simplex virus","herpes virus","herpes zoster","high blood pressure","high blood pressure in the lungs","high blood sugar","high triglycerides","high tumor burden","histoplasma capsulatum","histoplasmosis","history of cancer or radiation therapy","history of stevens-johnson syndrome","hit","hiv","aids","honeycombing","hpv","hsv","human immunodeficiency virus","human papillomavirus","hus","hypercapnic respiratory failure","hyperglycemia","hyperlipidemia","hypersensitivity","hypertension","hypertension-related complications","hypertensive crisis","hypomania","hypoxemic respiratory failure","idiopathic interstitial pneumonia","idiopathic pulmonary arterial hypertension","idiopathic pulmonary fibrosis","idiopathic thrombocytopenic purpura","immune complex deposition","immune complex disease","immune deficiency","immune system dysfunction","immunocompromised","immunodeficiency","immunosuppressants","immunosuppressive therapy","immunotherapy","increased blood pressure in the portal vein","increased risk of bleeding","increased risk of dvt","increased risk of limb loss or amputation","increased risk of rupture or death","increased risk with peripheral neuropathy pad or infection","inflammation","inflammatory bowel disease","insulin resistance","insulin resistance syndrome","intellectual disability","interstitial lung disease","intestinal amoebiasis","intestinal strongyloidiasis","intubation","iron accumulation","iron-deficiency anemia","itp","jaundice","kayser-fleischer rings","kidney disease","kidney infection","kidney inflammation","kidney transplant","latent tuberculosis","leishmaniasis","leukemia","lipid metabolism disorders","lipid-lowering medication","liver abscess","liver cancer","liver failure","liver inflammation","liver transplant","löfgren syndrome","lung cancer","lung infection","lung scarring","lupus","lupus nephritis","lymphoma","mac","maculopapular rash","malignancy risk","mandibular advancement device","manic episode","maternal complications","may require amputation of affected limb or organ","mdr-tb","medication noncompliance","melanoma","meningitis","metabolic disorders","metabolic surgery","metabolic syndrome","metastasis","methamphetamine abuse","methicillin-resistant staphylococcus aureus","mild cognitive impairment","mrsa","mucous membrane involvement","multi-organ failure","multidrug-resistant tuberculosis","multiple medication allergies","multiple sclerosis","mycobacterium avium complex","mycobacterium tuberculosis","mycophenolate mofetil","nafld","nah","nash","necrotizing fasciitis","nephritis","neurological symptoms","neutropenia","neutropenic sepsis","neutrophil function disorder","nihilistic delusions","non-alcoholic disease","non-alcoholic steatohepatitis","non-caseating granulomas","non-healing wounds","non-healing wounds or ulcers on the feet or toes","nonadherence to therapy","noncompliance with treatment","obesity","obstructive sleep apnea","oimpairment","oncologist","opioid abuse","opportunistic infections","osteomyelitis","pain at rest or non-healing wounds","pancreatic insufficiency","pancreatitis","parasitic infection","passive suicidal ideation","pcp","pericardial abscess","phosphodiesterase inhibitors","pleural drainage","pleural effusion","pneumocystis jirovecii pneumoni","pneumocystis pneumonia","pneumocystis pneumonia","pneumonitis","polydrug abuse","polypharmacy","portal hypertension","pregnancy","pregnant","prescription drug abuse","primary biliary cholangitis","primary sclerosing cholangitis","productive cough","prophylaxis","proteinuria","protozoan infection","pseudomembranous colitis","psoriasis","psychiatric symptoms","psychosis","psychotic depression","pulmonary arterial hypertension","pulmonary bullae","pulmonary embolism","pulmonary exacerbation","pulmonary fibrosis","pulmonary histoplasmosis","pulmonary hypertension","pulmonary hypertension-related complications","pulmonary infarction","pulmonary infiltrates","pulmonary symptoms","pulmonary vascular disease","pulmonary vasodilators","pyelonephritis","radiation therapy","raynaud's disease","raynaud's phenomenon","renal dysfunction","renal failure","resistant hypertension","respiratory failure","restrictive lung disease","retinitis","retrovirus","rheumatoid arthritis","right heart failure","sarcoidosis","sarcoma","schizoaffective disorder","schizophrenia","secondary hypertension","separation of the layers of the aortic wall","septic complications","septic embolism","serum sickness","severe asthma","severe blockage of arteries in the legs or feet","severe infection","severe liver disease","severe lung disease","severe pulmonary valve stenosis","shortness of breath","sickle cell anemia","sirolimus","sirs","sjogren's syndrome","skin detachment","smoker's lung","solid organ transplantation within the last 6 months","solid tumors","somatic delusions","splenic abscess","spontaneous bacterial peritonitis","squamous cell carcinoma","statins","status asthmaticus","steatosis","steroid-dependent asthma","stevens-johnson syndrome","stroke","strongyloides stercoralis","strongyloidiasis","substance abuse","substance-induced psychosis","sudden onset of severe chest or back pain","suicidal ideation","suicidal intent","suicidal planning","suicidal thoughts","syncope","systemic disease","systemic infection","systemic inflammatory response syndrome","systemic lupus erythematosus","systemic therapy","t-helper cells","tacrolimus","tb","teratogenicity","thick mucus","thoracentesis","threatening behavior","thromboangiitis obliterans","thrombocytopenia","thrombosis","thrombotic thrombocytopenic purpura","toxic epidermal necrolysis","toxoplasma gondii","toxoplasmosis","transaminases","transplant rejection","transudate","traumatic brain injury","treatment resistance","trichomoniasis","trimethoprim-sulfamethoxazole","ttp","tuberculosis","tuberculous lymphadenitis","tuberculous meningitis","tuberculous pericarditis","tumor","type 2 diabetes","type 2 diabetes mellitus","vancomycin-resistant enterococcus","varices","vasculitis","vasculitis lupus","vasospastic disorder","venous thromboembolism","ventilator dependence","viral hepatitis","visual hallucinations","vitamin-deficiency anemia","vre","vte","weight loss surgery","wilson's disease");
		boolean t =containsAny(checkWords,s);
		if(t==true)
			return 1;
		
		return 0;
	}
	public LinkedList res() {
		System.out.println("o3 fin");
		o3.display();
		return o3;
	}
	public static <T> boolean containsAny(List<T> l1, List<T> l2) {
        for (T elem : l1) {
            if (l2.contains(elem)) {
                return true;
            }
        }
        return false;
}
}
//passed linked list (recipient ids-shortlisted after bmi)
//traverse through linked list //constructor
	//connection to db and pick medical history as per rec id
	//in method per recipient id create keywords from the medical history descrp (string->token->stop words removal)
	//output in the form of arraylist
	//pass this list to check method
	//method to check if arraylist contains(given words-pulmo, diabetes,kidney diseases) then reject the recipient id else accept it
	//and return it to the calling function
	//check if the arraylist is empty or not, if empty return no match and call no match found email
	//else continue
	//return these recipient ids as linked list to the patient_status filter 