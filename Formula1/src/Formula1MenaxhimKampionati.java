import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Formula1MenaxhimKampionati implements MenaxhimKampionati{
    List<ShoferiFormula1> listaShofereve = new ArrayList<>(); //lista e shofereve
    List<ShoferiFormula1> listaShofereveTemp = new ArrayList<>();
    List<List<ShoferiFormula1>> pozicionetPerfundimtareGara = new ArrayList<>();
    //liste liste, ruan pozicionet e gares
    List<Data> dataGares = new ArrayList<>();//datat qe kryhen garat
    Scanner sc = new Scanner(System.in);
    //implementohen objektet per te aksesuar file me paths te caktuara dhe scanners perkates per ti lexuar
    File shoferetFile = new File("shoferetData.txt");
    File garatFile = new File("garat.txt");
    File datatFile = new File("data.txt");
    Scanner readShoferet = new Scanner(shoferetFile);
    Scanner readGarat = new Scanner(garatFile);
    Scanner readData = new Scanner(datatFile);

    int i, j; //perdoren ne cikle

    public Formula1MenaxhimKampionati() throws FileNotFoundException {
    }//printWriter exception

    public void lexoFile() {
        //krijojme nje objekt shoferi dhe i vendosim atributet perkatese deri ne momentin qe nuk ka rresht tjeter
        //veprohet njesoj per te 3 files
        while (readShoferet.hasNextLine()) {
            ShoferiFormula1 shoferiFile = new ShoferiFormula1();
            listaShofereve.add(shoferiFile);
            String emri = readShoferet.next();
            shoferiFile.setEmri(emri);
            String skuadra = readShoferet.next();
            shoferiFile.setSkuadra(skuadra);
            int nrVend1 = Integer.parseInt(readShoferet.next());
            shoferiFile.setNrVendPare(nrVend1);
            int nrVend2 = Integer.parseInt(readShoferet.next());
            shoferiFile.setNrVendiDyte(nrVend2);
            int nrVend3 = Integer.parseInt(readShoferet.next());
            shoferiFile.setNrVendiTrete(nrVend3);
            int piket = Integer.parseInt(readShoferet.next());
            shoferiFile.setPiket(piket);
            int garat = Integer.parseInt(readShoferet.next());
            shoferiFile.setNrGarave(garat);
        }
        while (readGarat.hasNextLine()) {
            List<ShoferiFormula1> tempListaShofereve = new ArrayList<>();
            //ruajme shoferet e 1 gare, futemi ne loop
            for (i = 0; i < listaShofereve.size(); i++) {
                //per aq shofere sa jane, merr te dhenat dhe i fut ne liste, pra njekohesisht pozicionet
                ShoferiFormula1 shofer = new ShoferiFormula1();
                String emri = readGarat.next();
                shofer.setEmri(emri);
                String skuadra = readGarat.next();
                shofer.setSkuadra(skuadra);
                tempListaShofereve.add(shofer);
            }
            //shtojme rezultatet ne listen e pozicioneve
            pozicionetPerfundimtareGara.add(tempListaShofereve);
        }
        while (readData.hasNextLine()) {
            Data data = new Data();
            dataGares.add(data);
            int viti = Integer.parseInt(readData.next());
            data.setViti(viti);
            int muaji = Integer.parseInt(readData.next());
            data.setMuaji(muaji);
            int dita = Integer.parseInt(readData.next());
            data.setDita(dita);
        }
    }

    //me poshte jane implementuar metodat qe bejne update filet e shofereve, garave dhe datave.
    public void updateShoferetFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(shoferetFile);
        //shkruajme file nga e para per te gjithe shoferet
        for (i = 0; i< listaShofereve.size();i++){
            //nese jemi ne indexin e fundit bejme print jo println pasi jep NoSuchElement Exception
            if (i == listaShofereve.size()-1){
                writer.print((listaShofereve.get(i).getEmri()+" "+listaShofereve.get(i).getSkuadra() + " " +
                        listaShofereve.get(i).getNrVendPare() + " " + listaShofereve.get(i).getNrVendiDyte() + " "
                        + listaShofereve.get(i).getNrVendiTrete() + " " + listaShofereve.get(i).getPiket() + " " +
                        listaShofereve.get(i).getNrGarave()));
                break;
            }
            writer.println(listaShofereve.get(i).getEmri()+" "+listaShofereve.get(i).getSkuadra() + " " +
                    listaShofereve.get(i).getNrVendPare() + " " + listaShofereve.get(i).getNrVendiDyte() + " "
                    + listaShofereve.get(i).getNrVendiTrete() + " " + listaShofereve.get(i).getPiket() + " " +
                    listaShofereve.get(i).getNrGarave());
        }
        writer.close();
    }
    public void updateGaratFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(garatFile);
        int counter = 0;
        for (i = 0; i < pozicionetPerfundimtareGara.size(); i++){
            //aq shofere sa ka gara
            counter++;
            if (counter>1){
                writer.print("\n");
            }
            for (j = 0; j < pozicionetPerfundimtareGara.get(i).size();j++){
                if (j == pozicionetPerfundimtareGara.get(i).size()-1) {
                    writer.print(pozicionetPerfundimtareGara.get(i).get(j).getEmri() + " " +
                            pozicionetPerfundimtareGara.get(i).get(j).getSkuadra());

                }else writer.println(pozicionetPerfundimtareGara.get(i).get(j).getEmri() + " " +
                        pozicionetPerfundimtareGara.get(i).get(j).getSkuadra());
            }
        }
        writer.close();
    }

    public void updateDataFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(datatFile);
        //rishkruajme cdo date qe kane ndodhur garat
        for (i = 0; i < dataGares.size(); i++){
            if (i == dataGares.size()-1){
                writer.print(dataGares.get(i).getViti() + " " + dataGares.get(i).getMuaji() + " " +
                        dataGares.get(i).getDita());
                break;
            }
            writer.println(dataGares.get(i).getViti() + " " + dataGares.get(i).getMuaji() + " " +
                    dataGares.get(i).getDita());
        }
        writer.close();
    }


    public void krijoShofer(){
        if (listaShofereve.size()>=10){
            System.err.println("Nuk mund te kete me shume se 10 shofere");
        }else {
            ShoferiFormula1 shoferiObj = new ShoferiFormula1();
            System.out.println("Fusni emrin e shoferit");
            shoferiObj.setEmri(sc.next());
            System.out.println("Fusni skuadren");
            shoferiObj.setSkuadra(sc.next());
            listaShofereve.add(shoferiObj);
            //kontrollojme sa shofere ka nje skuader
            int foundTimes = 0;
            for (i = 0; i < listaShofereve.size(); i++) {
                if (shoferiObj.getSkuadra().equalsIgnoreCase(listaShofereve.get(i).getSkuadra())) {
                    foundTimes++;//gjithmone ndodhet 1 here
                    if (foundTimes >= 2) {
                        System.out.println("Ekziston nje shofer per kete skuader!\n");
                        listaShofereve.remove(i);
                        break;
                    }
                }
            }
            System.out.println();
        }
    }
    public void fshiShofer(){
        //gjejme shoferin dhe e heqim
        if (listaShofereve.size()>0) {
            System.out.println("Fusni emrin e shoferit qe do te hiqni");
            String emri = sc.next();
            //loop cdo objekt shoferi ne liste, nese gjehet emri e heqim
            for (i = 0; i < listaShofereve.size(); i++) {
                if (emri.equalsIgnoreCase(listaShofereve.get(i).getEmri())) {
                    listaShofereve.remove(i);
                }
            }
        }else System.err.println("0 shofere!");
    }
    public void ndryshoShofer(){
        if (listaShofereve.size()>0) {
            System.out.println("Fusni emrin e skuadres qe doni t'i ndryshoni shoferin");
            String skuadra = sc.next();
            for (i = 0; i < listaShofereve.size(); i++) {
                if (skuadra.equalsIgnoreCase(listaShofereve.get(i).getSkuadra())) {
                    System.out.println("Fusni emrin e shoferit te ri");
                    listaShofereve.get(i).setEmri(sc.next());
                }
            }
        }else System.err.println("0 shofere!");
    }
    public void shfaqStatistika(){
        if (listaShofereve.size()>0) {
            System.out.println("Fusni emrin e shoferit per te cilin do te shohesh te dhenat");
            String emri = sc.next();
            boolean foundFlag = false;
            for (i = 0; i < listaShofereve.size(); i++) {
                if (emri.equalsIgnoreCase(listaShofereve.get(i).getEmri())) {
                    System.out.println(listaShofereve.get(i).toString() + "\n");
                    foundFlag = true;
                    break;
                }
            }
            if (!foundFlag){
                System.err.println("Shoferi nuk u gjet!");
            }
        }else System.err.println("0 shofere!\n");
    }

    public void shfaqTeGjitheShoferet(){
        //toString per shoferet, shfaq te dhenat
        if (listaShofereve.size()>0) {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            for (i = 0; i < listaShofereve.size(); i++) {
                System.out.println("Shoferi " + (i+1) + ": " + listaShofereve.get(i).toString());
            }
            System.out.println();
        }else System.err.println("0 shofere\n");
    }

    public String  shfaqTeGjitheShoferetGui(){
        StringBuilder result = new StringBuilder();
        if (listaShofereve.size()>0) {
            result.append("--------------------------------------------------------------------------------------------------------------------------------");
            result.append("\n");
            for (i = 0; i < listaShofereve.size(); i++) {
                result.append("Shoferi ").append(i + 1).append(": ").append(listaShofereve.get(i).toString());
            }
            result.append("\n");
        }else result.append("0 shofere\n");
        //pasi i bejme append stringut, e bejme return per ta shfaqur ne swing
        return result.toString();
    }

    public void pozicionetCfaredo(){
        Random rand = new Random();
        int numOfLoops = listaShofereve.size();
        for (i = 0; i< numOfLoops; i++){
            int random = rand.nextInt(listaShofereve.size());
            listaShofereveTemp.add(listaShofereve.get(random));
            listaShofereve.remove(random);
        }
        for (i = 0; i < listaShofereveTemp.size(); i++){
            listaShofereve.add(listaShofereveTemp.get(i));
        }
        //zbrazim listen temporary
//        for (i = listaShofereveTemp.size(); i>0; i--){
//            listaShofereveTemp.remove(0);
//        }
        listaShofereveTemp = new ArrayList<>();
        //u vendosen te gjithe garuesit ne pozicione random qe korrespondojne me indekset e tyre +1
        //psh ne index 0 gjendet garuesi i vendit te 1, indexi 1 ai qe nis ne vendin e dyte etj
    }

    public void pozicionetCfaredoShans(){
        Random rand = new Random();
        int numOfLoops = listaShofereve.size();
        double shoferiShans = Math.random();//0-1
        for (i = 0; i< numOfLoops; i++) {
            int random = rand.nextInt(listaShofereve.size());//0-lista.size
            if (i == 0) {
                //shoferi qe fiton garen ka shanset ne baze te pozicionit
                if (shoferiShans > 0 && shoferiShans <= 0.4) {
                    listaShofereveTemp.add(listaShofereve.get(0));
                    listaShofereve.remove(0);
                } else if (shoferiShans > 0.4 && shoferiShans <= 0.7) {
                    listaShofereveTemp.add(listaShofereve.get(1));
                    listaShofereve.remove(1);
                } else if (shoferiShans > 0.7 && shoferiShans <= 0.8) {
                    listaShofereveTemp.add(listaShofereve.get(2));
                    listaShofereve.remove(2);
                } else if (shoferiShans > 0.8 && shoferiShans <= 0.9) {
                    listaShofereveTemp.add(listaShofereve.get(3));
                    listaShofereve.remove(3);
                } else if (shoferiShans > 0.9 && shoferiShans <= 0.92) {
                    listaShofereveTemp.add(listaShofereve.get(4));
                    listaShofereve.remove(4);
                } else if (shoferiShans > 0.92 && shoferiShans <= 0.94) {
                    listaShofereveTemp.add(listaShofereve.get(5));
                    listaShofereve.remove(5);
                } else if (shoferiShans > 0.94 && shoferiShans <= 0.96) {
                    listaShofereveTemp.add(listaShofereve.get(6));
                    listaShofereve.remove(6);
                } else if (shoferiShans > 0.96 && shoferiShans <= 0.98) {
                    listaShofereveTemp.add(listaShofereve.get(7));
                    listaShofereve.remove(7);
                } else {
                    listaShofereveTemp.add(listaShofereve.get(8));
                    listaShofereve.remove(8);
                }
            } else{//per shoferet e tjere perdorim random
                listaShofereveTemp.add(listaShofereve.get(random));
                listaShofereve.remove(random);
            }
        }
        //popullojme listen origjinale dhe zbrazim temp
        for (i = 0; i < listaShofereveTemp.size(); i++){
            listaShofereve.add(listaShofereveTemp.get(i));
        }
//        for (i = listaShofereveTemp.size(); i>0; i--){
//            listaShofereveTemp.remove(0);
//        }
        listaShofereveTemp = new ArrayList<>();
    }

    public void updateStatistikat(){
        //shtojme nr garash me 1, piket sipas pozicionit dhe nr te vendit 1, 2, dhe 3
        for (i = 0; i< listaShofereve.size(); i++){
            listaShofereve.get(i).setNrGarave(listaShofereve.get(i).getNrGarave()+1);
        }
        int[] piket = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        for (i = 0; i < listaShofereve.size(); i++){
            listaShofereve.get(i).setPiket(listaShofereve.get(i).getPiket() + piket[i]);
        }
        listaShofereve.get(0).setNrVendPare(listaShofereve.get(0).getNrVendPare()+1);
        listaShofereve.get(1).setNrVendiDyte(listaShofereve.get(1).getNrVendiDyte()+1);
        listaShofereve.get(2).setNrVendiTrete(listaShofereve.get(2).getNrVendiTrete()+1);
    }

    public String krijoDateGui(){//mund te implementohet brenda ne metoden tjeter, kodi me i organizuar
        //si fillim marrim nje date te rastesishme
        StringBuilder dataResult = new StringBuilder();
        Random rand = new Random();
        Data data = new Data();
        do {
            data.setViti(rand.nextInt(22)+2015);
        }while (data.getViti()<0 && data.getViti()<2030);
        do {
            data.setMuaji(rand.nextInt(12)+1);
        }while (data.getMuaji()<0 || data.getMuaji()>12);

        do {
            data.setDita(rand.nextInt(31)+1);
        }while (data.getDita()<0 || data.getDita()>31);
        dataResult.append(data.toString()).append("\n");
        dataGares.add(data);
        return dataResult.toString();
    }

    public void simuloGare(){
        //krijon nje date random
        Random rand = new Random();
        Data data = new Data();
        do {
            data.setViti(rand.nextInt(22)+2015);
        }while (data.getViti()<0 && data.getViti()<2030);
        do {
            data.setMuaji(rand.nextInt(12)+1);
        }while (data.getMuaji()<0 || data.getMuaji()>12);

        do {
            data.setDita(rand.nextInt(31)+1);
        }while (data.getDita()<0 || data.getDita()>31);
        System.out.println(data.toString());
        dataGares.add(data);
        //pasi vume pozicionet ne baze te rastit, simulojme garen

        //duhen bere randomize pozicionet, metoda ekzekutohet ne switch te menuse kryesore
        System.out.println("\nPozicionet perfundimtare: ");
        for (i = 0; i < listaShofereve.size(); i++){
            listaShofereveTemp.add(listaShofereve.get(i));
            }//i fusim elementet ne nje temp
        for (i = 0; i< listaShofereveTemp.size(); i++){
            System.out.println((i+1) + ": " + listaShofereveTemp.get(i).getEmri());
        }//printojme pozicionet e reja
        updateStatistikat();
        pozicionetPerfundimtareGara.add(listaShofereveTemp);
        listaShofereveTemp = new ArrayList<>();
        System.out.println();
    }

    public String simuloGareGui(){
        StringBuilder result = new StringBuilder();
        result.append(krijoDateGui());
        //pasi vume pozicionet ne baze te rastit, simulojme garen
        //randomize ndodhet ne switch menu
        result.append("\nPozicionet perfundimtare: ");
        for (i = 0; i < listaShofereve.size(); i++){
            listaShofereveTemp.add(listaShofereve.get(i));
        }//i fusim elementet ne nje temp
        for (i = 0; i< listaShofereveTemp.size(); i++){
            result.append(i + 1).append(": ").append(listaShofereveTemp.get(i).getEmri());
            result.append("\n");
        }//printojme pozicionet e reja te gares
        updateStatistikat();//vendosim piket perkatese
        pozicionetPerfundimtareGara.add(listaShofereveTemp);//pozicionet perfundimtare ose listaShofereve testo
        listaShofereveTemp = new ArrayList<>();
        result.append("\n");
        return result.toString();
    }

    public void sortDesc(){//ne baze te pikeve
        for (i = 0; i< listaShofereve.size(); i++) {
            for (j = i + 1; j < listaShofereve.size(); j++) {
                if (listaShofereve.get(i).getPiket() < listaShofereve.get(j).getPiket()) {
                    Collections.swap(listaShofereve, i, j);
                    //nese piket =, vendoset garuesi me me shume fitore
                }else if (listaShofereve.get(i).getPiket() == listaShofereve.get(j).getPiket()){
                    if (listaShofereve.get(i).getNrVendPare() < listaShofereve.get(j).getNrVendPare()) {
                        Collections.swap(listaShofereve, i, j);
                    }
                }
            }
        }
    }

    public void sortDescFitore(){
        for (i = 0; i< listaShofereve.size(); i++) {
            for (j = i + 1; j < listaShofereve.size(); j++) {
                if (listaShofereve.get(i).getNrVendPare() < listaShofereve.get(j).getNrVendPare()) {
                    Collections.swap(listaShofereve, i, j);
                }
            }
        }
    }

    public void sortAsc(){
        for (i = 0; i< listaShofereve.size(); i++) {
            for (j = i + 1; j < listaShofereve.size(); j++) {
                if (listaShofereve.get(i).getPiket() > listaShofereve.get(j).getPiket()) {
                    Collections.swap(listaShofereve, i, j);
                }else if (listaShofereve.get(i).getPiket() == listaShofereve.get(j).getPiket()){
                    if (listaShofereve.get(i).getNrVendPare() < listaShofereve.get(j).getNrVendPare()) {
                        Collections.swap(listaShofereve, i, j);
                    }
                }
            }
        }
    }

    public String kerkoSipasShoferit(String emri){
        StringBuilder result = new StringBuilder();
        //kerkoje emrin ne te gjithe garat
        for (i = 0; i < pozicionetPerfundimtareGara.size(); i++){
            for (j = 0; j<pozicionetPerfundimtareGara.get(i).size();j++){
                if (emri.equalsIgnoreCase(pozicionetPerfundimtareGara.get(i).get(j).getEmri())){
                    result.append(dataGares.get(i).toString()).append("\nPozicioni ").append((j+1)).append("\n");
                    result.append("\n");
                    break;
                }
            }
        }
        return result.toString();
    }

    public void renditGaratSipasDatesAscending(){
        for (i = 0; i< dataGares.size(); i++) {
            for (j = i + 1; j < dataGares.size(); j++) {
                //krahasojme te gjitha datat ne baze te vitit, muajit dhe dites
                if (dataGares.get(i).getViti() > dataGares.get(j).getViti()) {
                    Collections.swap(dataGares, i, j);
                    Collections.swap(pozicionetPerfundimtareGara, i, j);
                }else if(dataGares.get(i).getViti() == dataGares.get(j).getViti()){
                    if (dataGares.get(i).getMuaji() > dataGares.get(j).getMuaji()){
                        Collections.swap(dataGares, i, j);
                        Collections.swap(pozicionetPerfundimtareGara, i, j);
                    }else if(dataGares.get(i).getMuaji() == dataGares.get(j).getMuaji()){
                        if (dataGares.get(i).getDita() > dataGares.get(j).getDita()){
                            Collections.swap(dataGares, i, j);
                            Collections.swap(pozicionetPerfundimtareGara, i, j);
                        }
                    }
                }
            }
        }
    }

    public String renditGaratGui(){
        StringBuilder result = new StringBuilder();
        for (i = 0; i < pozicionetPerfundimtareGara.size(); i++) {
            result.append("Rezultatet e gares se dates ").append(dataGares.get(i).toString()).append(":\n");
            for (j = 0; j < pozicionetPerfundimtareGara.get(i).size(); j++) {
                result.append(j + 1).append(". ").append(pozicionetPerfundimtareGara.get(i).get(j).getEmri()).append(" ").append(pozicionetPerfundimtareGara.get(i).get(j).getSkuadra());
                result.append("\n");
            }
            result.append("\n");
        }
        //stringBuilder pasi rezultati do shfaqet ne gui textbox
        return result.toString();
    }
    public void shtoGareManualisht(){
        Data data = new Data();
        //marrim daten si input
        System.out.println("Vendos daten qe eshte zhvilluar gara ne formatin YY MM DD");
        do {
            data.setViti(sc.nextInt());
            if (data.getViti()<0){
                System.out.println("Ju lutem fusni nje vit pozitiv");
            }
        }while (data.getViti()<0);
        do {
            data.setMuaji(sc.nextInt());
            if (data.getMuaji()<0 || data.getMuaji() >12){
                System.out.println("Ju lutem fusni nje muaj nga 1-12");
            }
        }while (data.getMuaji()<0 || data.getMuaji()>12);
        do {
            data.setDita(sc.nextInt());
            if (data.getDita()<0 || data.getDita() >31){
                System.out.println("Ju lutem fusni nje dite nga 1-31");
            }
        }while (data.getDita()<0 || data.getDita()>31);
        shfaqTeGjitheShoferet();
        System.out.println("Vendos pozicionet e garuesve manualisht: ");
        int count = listaShofereve.size();
        for (i = 0; i< count; i++){
            System.out.println("Vendi i " + (i+1) + ": ");
            int j;
            do { //vazhdon jep input deri ne mom qe eshte ne range specified
                j = sc.nextInt();
                if (j<0 || j > listaShofereve.size()){
                    System.out.println("Ju lutem zgjidhni shoferet nga 1 deri ne " + listaShofereve.size());
                }
            }while (!(j>0 && j <= listaShofereve.size()));
            listaShofereveTemp.add(listaShofereve.get(j-1));
            listaShofereve.remove(j-1);
            if (listaShofereve.size()>0) {
                shfaqTeGjitheShoferet();
            }
        }
        for (i = 0; i < listaShofereveTemp.size(); i++){
            listaShofereve.add(listaShofereveTemp.get(i));
        }
//        for (i = listaShofereveTemp.size(); i>0; i--){
//            listaShofereveTemp.remove(0);
//        }
        listaShofereveTemp = new ArrayList<>();

        updateStatistikat();
        shfaqTeGjitheShoferet();
        dataGares.add(data);
        pozicionetPerfundimtareGara.add(listaShofereve);
    }
    public static void menu(Formula1MenaxhimKampionati formula1, Scanner sc) throws FileNotFoundException {
        formula1.lexoFile();
        int ch;
        do {
            System.out.println("Zgjidh nje opsion\n1. Krijo nje shofer\n2. Fshi nje shofer\n" +
                    "3. Ndrysho shoferin e nje skuadre\n4. Shfaq te dhenat per 1 shofer\n" +
                    "5. Shfaq te gjithe shoferet\n6. Simulo nje gare\n7. Simulo gare me shans\n8. Fut nje gare manualisht\n9. Dil nga programi\n\nZgjedhja juaj:");
            ch = 0;
            ch = exception(sc, ch);
            switch (ch){
                case 1: {
                    formula1.krijoShofer();
                    break;
                }
                case 2: {
                    formula1.fshiShofer();
                    break;
                }
                case 3: {
                    formula1.ndryshoShofer();
                    break;
                }
                case 4: {
                    formula1.shfaqStatistika();
                    break;
                }
                case 5: {
                    formula1.sortDesc();
                    formula1.shfaqTeGjitheShoferet();
                    formula1.updateShoferetFile();
                    break;
                }
                case 6: {
                    formula1.pozicionetCfaredo();
                    formula1.simuloGare();
                    formula1.updateGaratFile();
                    formula1.updateDataFile();
                    formula1.updateShoferetFile();
                    break;
                }
                case 7: {
                    formula1.pozicionetCfaredoShans();
                    formula1.simuloGare();
                    formula1.updateGaratFile();
                    formula1.updateDataFile();
                    formula1.updateShoferetFile();
                    break;
                }
                case 8: {
                    formula1.shtoGareManualisht();
                    formula1.updateGaratFile();
                    formula1.updateDataFile();
                    formula1.updateShoferetFile();
                    break;
                }
                case 9: {
                    System.out.println("Mirupafshim!");
                    formula1.updateShoferetFile();
                    break;
                }
            }
        }while (ch!=9);
    }

    //exception handling for all inputs
    public static int exception(Scanner sc, int input){
        boolean Error = false;
        while (!Error){
            try {
                input = sc.nextInt();
                Error=true;
            } catch (InputMismatchException ime){
                System.err.println("Ju lutem fusni nje numer");
                sc.nextLine();// this consumes the invalid token
            }
        }
        return input;
    }
}