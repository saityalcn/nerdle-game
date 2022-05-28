import java.io.Serializable;

public class Statistics implements Serializable {
    private static final long serialVersionUID = 724237510432788580L;
    public int numberOfCompletedGames;
    public int numberOfUncompletedGames;
    public int numberOfSuccess;
    public int numberOfUnsuccess;
    public int numberOfGames;
    public double averageNumberOfRows;
	//public Time averageTime 
    private String fileName = "statistics.bin";

    public Statistics(){}
    
    private void increaseCompletedGameCount() {
    	this.numberOfCompletedGames++;
    }
    
    private void increaseUncompletedGameCount() {
    	this.numberOfUncompletedGames++;
    }
    
    private void increaseNumberOfSuccess() {
    	this.numberOfSuccess++;
    }
    
    private void increaseNumberOfUnsuccess() {
    	this.numberOfUnsuccess++;
    }
    
    private void changeAverageTime(int time) {
    	System.out.println("Time Changed");
    }
    
    private void changeAverageNumberOfRows(int rowCount) {
    	double totalRowCount = this.averageNumberOfRows * (numberOfCompletedGames-1);  
    	this.averageNumberOfRows = (double) (totalRowCount + rowCount) / numberOfCompletedGames;
    }
    
    public void initializeStatistics(){
    	FileReadWrite<Statistics> frw = new FileReadWrite<>();
    	if(frw.isFileExists(fileName)) {
    		Statistics s = frw.readData(fileName);
    		this.numberOfCompletedGames = s.numberOfCompletedGames;
    		this.numberOfUncompletedGames = s.numberOfUncompletedGames;
    		this.numberOfSuccess = s.numberOfSuccess;
    		this.numberOfUnsuccess = s.numberOfUnsuccess;
    		this.numberOfGames = s.numberOfGames;
    		this.averageNumberOfRows = s.averageNumberOfRows;
    	}
    	else {
    		this.numberOfCompletedGames = 0;
    		this.numberOfUncompletedGames = 0;
    		this.numberOfSuccess = 0;
    		this.numberOfUnsuccess = 0;
    		this.numberOfGames = 0;
    		this.averageNumberOfRows = 0.0;
    	}
    }
    
    public void finalizeGame(boolean isEndedWithSuccess, int rowCount, boolean isCompleted, int time) {
    	FileReadWrite<Statistics> fileReadWrite  = new FileReadWrite<>();
    	if(isCompleted) {
			increaseCompletedGameCount();
    		if(isEndedWithSuccess) {
    			increaseNumberOfSuccess();
    			changeAverageNumberOfRows(rowCount);
    			changeAverageTime(time);
    		}
    		else
    			increaseNumberOfUnsuccess();	
    	}
    	else 
    		increaseUncompletedGameCount();
    	
    	fileReadWrite.writeData(this.fileName, this);
    }
    
    
}
