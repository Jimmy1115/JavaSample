package SampleCode;
import javax.lang.model.util.SimpleElementVisitor7;
import java.io.*;

public class SampleIO {
    SampleIO(int type){
        if (type ==1){
            // createFile();
            // getFileList();
            // readFileByByte();
            // writeFileByByte();
            // readFileByChar();
            // writeFileByChar();
            // readFileByBufferReader();
            // writeFileByBufferWriter();
            // systemInOut();
            // systemInOutToFile();
            // new SaverAddIntBean().write();
            new ReadAddIntBean().read();
        }
    }
    void createFile(){
        File f = new File("NewFile.txt");
        System.out.println("檔案是否存在？" + f.exists());
        if (!f.exists()){
            System.out.print("利用 createNewFile() 建立新檔案," + "是否建立成功 ?");

            try {
                System.out.println(f.createNewFile());
            }catch (IOException e){
                System.out.println(e);
            }
            System.out.println("檢查新建立檔案是否存在？" + f.exists());
        }
    }
    void getFileList(){
        // String path = "C:\\Users\\Jimmy Chen\\IdeaProjects\\Java\\";
        String path = "C:/Users/Jimmy Chen/IdeaProjects/Java/";
        File f1 = new File(path);
        MyFilter filter = new MyFilter();

        String[] fileList = f1.list(filter);
        for (int i = 0; i < fileList.length; i++) {
            File f2 = new File(path + fileList[i]);
            if (f2.isDirectory()){
                System.out.println("是目錄:" +  fileList[i]);
            }
            else{
                System.out.println("是檔案:" + fileList[i]);
            }
        }
    }
    static class MyFilter implements FilenameFilter{    // 檔名過濾器
        private String type;
        public MyFilter(){
        }
        public MyFilter(String type){
            this.type = type;
        }
        public boolean accept(File dir,String name){
            if (type == null){  // 沒指定副檔名時，當作找全部的檔案
                return true;
            }
            return name.endsWith(type);
        }
    }
    void readFileByByte(){
        String fileName = "NewFile.txt";
        byte[] buffer;
        int totalBytes;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            totalBytes = fis.available();
            System.out.println("顯示可讀取資料位元組總數:" + totalBytes + "bytes.");
            System.out.println("檔案內容:");
            System.out.println("--------------");

            // buffer = new byte[1];
            // while (fis.read(buffer) != -1){
            //     System.out.print((char)buffer[0]);
            // }
            buffer = new byte[totalBytes];
            while (fis.read(buffer) == totalBytes){
                String s = new String(buffer);
                System.out.print(s);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                fis.close();
            }
            catch (IOException e){}
            catch (NullPointerException e){}
        }
    }
    void writeFileByByte(){
        String s = "Java 輸入與輸出(Java函式庫)";
        byte[] data = s.getBytes();
        System.out.println("將字串\"" + s +"\"寫到檔案");
        System.out.println("資料長度:" + data.length + "bytes.");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("SampleFile2.txt", true);
            fos.write(data);
        }
        catch (IOException e){}
        finally {
            try {
                fos.close();
            }
            catch (IOException e){}
        }
    }
    void readFileByChar(){
        String fileName = "NewFile.txt";
        char[] buffer;
        FileReader fr = null;
        try {
            System.out.println("檔案內容:");
            System.out.println("--------------");
            fr = new FileReader(fileName);
            buffer = new char[1];
            while (fr.read(buffer) != -1){
                String s = new String(buffer);
                System.out.print(s);
            }
        }
        catch (IOException e){}
        finally {
            try {
                fr.close();
            }
            catch (IOException e){}
            catch (NullPointerException e){}
        }
    }
    void writeFileByChar(){
        String data = "Java 輸入與輸出(Java函式庫)";
        System.out.println("將字串\"" + data +"\"寫到檔案");
        System.out.println("資料長度:" + data.length() + "bytes.");

        FileWriter fw = null;
        try {
            fw = new FileWriter("SampleFile4.txt", false);
            fw.write(data);

        }
        catch (IOException e){}
        finally {
            try {
                fw.close();
            }
            catch (IOException e){}
        }
    }
    void readFileByBufferReader(){
        String fileName = "NewFile.txt";
        FileReader fr = null;
        BufferedReader br = null;
        try {
            System.out.println("檔案內容:");
            System.out.println("--------------");
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null){
                System.out.println(s);
            }
        }
        catch (IOException e){}
        finally {
            try {
                br.close();
                fr.close();;
            }
            catch (IOException e){}
            catch (NullPointerException e){}
        }
    }
    void writeFileByBufferWriter(){
        FileWriter fw = null;
        BufferedWriter bw = null;
        String[] buf = new String[2];
        buf[0] = "\n310-055";
        buf[1] = "SCJP 5.0";
        try {
            fw = new FileWriter("SampleFile4.txt", true);
            bw = new BufferedWriter(fw);
            for (int i = 0; i < buf.length; i++) {
                bw.write(buf[i]);
                bw.newLine();
            }
        }
        catch (IOException e){}
        finally {
            try {
                bw.close();
            }
            catch (IOException e){}
        }
    }

    void systemInOut(){
        InputStreamReader in = null;
        BufferedReader br = null;
        PrintWriter pw = null;

        try {
            in = new InputStreamReader(System.in);
            br = new BufferedReader(in);
            pw = new PrintWriter(System.out, true);
            String s;
            while (true) {
                s = br.readLine();
                if (s.equals("quit")) {
                    break;
                }
                pw.println("\n你所輸入的內容是:" + s);
            }
        }
        catch (IOException e){}
        finally {
            try {
                br.close();
                pw.close();
            }
            catch (IOException e){}
        }
    }
    void systemInOutToFile(){
        InputStreamReader in = null;
        BufferedReader br = null;
        PrintStream out = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos= null;

        try {
            in = new InputStreamReader(System.in);
            br = new BufferedReader(in);
            fos = new FileOutputStream("OutputFile.Txt");
            bos = new BufferedOutputStream(fos);
            out = new PrintStream(bos, true);
            System.setOut(out);
            String s;
            while ((s = br.readLine()).length() != 0) {
                if (s.equals("quit")) {
                    break;
                }
                out.println("\n你所輸入的內容是:" + s);
            }
            out.flush();
        }
        catch (IOException e){}
        finally {
            // try {
            out.close();
                // br.close();
                // pw.close();
            // }
            // catch (IOException e){}
        }
    }

    // 物件序列化
    // AddInt必須是獨立的 class
    public class SaverAddIntBean{
        public void write(){
            String path = "IntBean.ser";
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            AddInt ai = new AddInt();
            ai.calc(1,2,3,4);

            try{
                fos = new FileOutputStream(path);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(ai);
                System.out.println("Successfully");
            }
            catch (Exception e){
                System.out.println("Error");
            }
            finally {
                try {
                    oos.close();
                    fos.close();
                }
                catch (IOException e){}
            }
        }
    }
    public class ReadAddIntBean{
        void read(){
            String path = "IntBean.ser";
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            AddInt ai = null;

            try{
                fis = new FileInputStream(path);
                ois = new ObjectInputStream(fis);
                ai = (AddInt) ois.readObject();
                if (ai != null){
                    System.out.println(ai.getSum());
                }
                else {
                    System.out.println("null");
                }
            }
            catch (IOException e){}
            catch (ClassNotFoundException e){}

            finally {
                try {
                    ois.close();
                    fis.close();
                }
                catch (IOException e){}
            }
        }
    }
}

