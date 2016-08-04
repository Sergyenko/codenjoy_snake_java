
public class DataHelper {

    public static String[] getBoard(String data) {
    	if(data != null) {
    	String[] tempArr = data.split("=");
//        System.out.println("data = " + data);
        	if(tempArr.length == 2) {
                int size = (int)Math.sqrt(tempArr[1].length());
                String[] board = new String[size];
                if(size > 0) {
                	int start = 0;
                	int end = size - 1;
                	for(int i = 0; i < size; i++) {
               			board[i] = tempArr[1].substring(start, end);
               			start = start + size;
               			end = end + size;
                	}
                }
                return board;
        	} else {
        		return null;
        	}
    	} else {
    		return null;
    	}
    }
}
