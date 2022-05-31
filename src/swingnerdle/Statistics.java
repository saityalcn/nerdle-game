package swingnerdle;

import java.io.Serializable;

public class Statistics implements Serializable {
    private static final long serialVersionUID = 724237510432788580L;
    public int numberOfCompletedGames;
    public int numberOfUncompletedGames;
    public int numberOfSuccess;
    public int numberOfUnsuccess;
    public double averageNumberOfRows;
    public String averageTime; 
    private String fileName = "statistics.bin";

    public Statistics(){}
    
	// tamamlanan bir oyunun ardindan tamamlanan oyun sayisini arttirir
    private void increaseCompletedGameCount() {
    	this.numberOfCompletedGames++;
    }
    
	// tamamlanmayan bir oyunun ardindan tamamlanmamis oyun sayisini arttirir
    private void increaseUncompletedGameCount() {
    	this.numberOfUncompletedGames++;
    }
    
	// basarili biten bir oyunun ardindan basarili oyun sayisini arttirir
    private void increaseNumberOfSuccess() {
    	this.numberOfSuccess++;
    }
    
	// basarisiz biten bir oyunun ardindan basarisiz oyun sayisini arttirir
    private void increaseNumberOfUnsuccess() {
    	this.numberOfUnsuccess++;
    }
    
	// ortalama surenin guncellenmesini saglar
    private void changeAverageTime(String time) {
    	this.averageTime=String.valueOf((Double.valueOf(averageTime)*(numberOfSuccess-1)+Double.valueOf(time))/(numberOfSuccess));
    }
    
	// basarili biten bir oyunun ardindan ortalama satir sayisini gunceller
    private void changeAverageNumberOfRows(int rowCount) {
    	double totalRowCount = this.averageNumberOfRows * (numberOfCompletedGames-1);  
    	this.averageNumberOfRows = (double) (totalRowCount + rowCount) / numberOfCompletedGames;
    }
    
	// eskiden kayitli istatistikler varsa onlari dosyadan ceker, yoksa istatistikleri 0 olarak ilklendirir
    public void initializeStatistics(){
    	FileReadWrite<Statistics> frw = new FileReadWrite<>();
    	if(frw.isFileExists(fileName)) {
            
    		Statistics s = frw.readData(fileName);
    		this.numberOfCompletedGames = s.numberOfCompletedGames;
    		this.numberOfUncompletedGames = s.numberOfUncompletedGames;
    		this.numberOfSuccess = s.numberOfSuccess;
    		this.numberOfUnsuccess = s.numberOfUnsuccess;
    		this.averageNumberOfRows = s.averageNumberOfRows;
                this.averageTime=s.averageTime;
    	}
    	else {
    		this.numberOfCompletedGames = 0;
    		this.numberOfUncompletedGames = 0;
    		this.numberOfSuccess = 0;
    		this.numberOfUnsuccess = 0;
    		this.averageNumberOfRows = 0.0;
                this.averageTime="0.0";
    	}
    }
    
    
	// biten bir oyunun ardindan istatistikleri guncellemeyi saglar
    public void finalizeGame(boolean isEndedWithSuccess, int rowCount, boolean isCompleted, String time) {
    	FileReadWrite<Statistics> fileReadWrite  = new FileReadWrite<>();
    	if(isCompleted) {
			increaseCompletedGameCount();	// oyun tamamlandiysa
    		if(isEndedWithSuccess) {
    			increaseNumberOfSuccess();	// oyun basarili tamamlandiysa
    			changeAverageNumberOfRows(rowCount);	// oyun basarili tamamlandiysa
    			changeAverageTime(time);	// oyun basarili tamamlandiysa
    		}
    		else
    			increaseNumberOfUnsuccess();	// oyun basarisiz tamamlandiysa
    	}
    	else 
    		increaseUncompletedGameCount();		// oyun tamamlanmadiysa
    	
    	fileReadWrite.writeData(this.fileName, this);
    }
    
	// istatistikleri yazdirirken gerekli metni hazirlar ve dondurur
    public String[] getString(){
        String[] str =new String[5];
        str[0]= "Yarıda Bırakılan Oyun Sayısı: " + numberOfUncompletedGames + "\n";
        str[1]= "Başarıyla Tamamlanan Oyun Sayısı: " + numberOfSuccess + "\n";
        str[2]= "Başarısızlıkla Sonuçlanan Oyun Sayısı: " + numberOfUnsuccess + "\n";
        str[3]= String.format("Başarıyla Tamamlanan Oyunların Ortalama Satır Sayısı: %.2f\n", averageNumberOfRows);
        str[4]= String.format("Başarıyla Tamamlanan Oyunların Ortalama Bitirme Süresi: %.2f \n", Double.valueOf(averageTime)); 

        return str;
    }

    
}
