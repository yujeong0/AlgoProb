import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class BlockPractice {

   public static class Block{
       private String hash;
       private String previousHash;
       private String data; //Transaction
   
       private long timeStamp;
       private int nonce;
       private String target = "00000";
       private int targetDepth = 5;
   
   
       public Block(String data,String previousHash ) {
           this.data = makeHashData(data);
           this.previousHash = previousHash;
           this.timeStamp = new Date().getTime();
           mineNewBlock();
       }
   
   
       /**
        * 신규 블록체인을 생성한다.
        */
       private void mineNewBlock(){
   
           // 조건에 맞는 Hash 값을 찾을 때까지 계속 반복한다.
           while(hash == null || !hash.substring(0, targetDepth).equals(target)) {
               nonce ++;
               hash = makeHashBlock();
           }
       }
   
   
       /**
        * Hash 값을 조회한다.
        */
       public String makeHashBlock() {
           return getSha256(
                       previousHash +
                       Long.toString(timeStamp) +
                       data +
                       Integer.toString(nonce)
           );
       }
   
       /**
        * 트랜잭션 데이터를 해싱처리한다.
        */
       public String makeHashData(String data) {
           return getSha256(data);
       }
   
       public String getSha256(String str) {
          String SHA;
           try{
               MessageDigest sh = MessageDigest.getInstance("SHA-256");
               sh.update(str.getBytes());
               byte byteData[] = sh.digest();
               StringBuffer sb = new StringBuffer();
               for (byte aByteData : byteData) {
                   sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
               }
               SHA = sb.toString();
           }catch(NoSuchAlgorithmException e){
               e.printStackTrace();
               SHA = null;
           }
           return SHA;
       }
   }
    public static void main(String[] args) {
       Block genesis = new Block("Genesis Block", "");
      System.out.println("nonce: " + genesis.nonce);
      System.out.println("data: Genesis Block");
      System.out.println("Previous hash: " + genesis.previousHash);
      System.out.println("Hash: " + genesis.data);
      
      System.out.println();
      
      Block sec = new Block("2nd", genesis.data);
      System.out.println("nonce: " + sec.nonce);
      System.out.println("data: 2nd");
      System.out.println("Previous hash: " + genesis.data);
      System.out.println("Hash: " + sec.hash);
      
      System.out.println();
      
      Block third = new Block("3rd", sec.hash);
      System.out.println("nonce: " + third.nonce);
      System.out.println("data: 3rd");
      System.out.println("Previous hash: " + sec.hash);
      System.out.println("Hash: " + third.hash);
   }
}