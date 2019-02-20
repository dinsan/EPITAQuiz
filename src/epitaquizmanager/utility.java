/* 
**********************
@Author: Loord Dinsan
Company: EPITA
Date   : 12/02/2019
**********************
 */


package epitaquizmanager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class utility {

    public boolean checkFileIsExting(String pathName) {
        File tmpDir = new File(pathName);
        boolean exists = tmpDir.exists();
        return exists;
    }

    public String RemoveFandLstring(String loginToken) {

        return loginToken.substring(1, loginToken.length() - 1);
    }

    public String GetPathNmae(String FileName) {

        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "src\\epitaquizmanager\\jsonData", FileName + ".json");

        return filePath.toString();
    }

}
