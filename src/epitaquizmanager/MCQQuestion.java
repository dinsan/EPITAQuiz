/* 
**********************
Authors: Loord Dinsan
Company: EPITA
Date   : 12/02/2019
**********************
 */
package epitaquizmanager;

import java.util.UUID;


public class MCQQuestion extends Question {

    /**
     * Default constructor
     */
    public MCQQuestion() {
    }

    //care new MCQ 
    public void CreateNewMCQ() {

        try {

            MCQQuestion mcq = new MCQQuestion();
            String uniqueID = UUID.randomUUID().toString();
            mcq.setID(uniqueID);
            mcq.setDifficulty("dinsan");
            mcq.setQuestion("What is object?");

            String[] Topics = {
                "jalapeno",
                "anaheim",
                "serrano",
                "habanero",
                "thai"
            };
            mcq.seTopics(Topics);

           curd createMCQ = new curd();
           createMCQ.create("mcqQuestion", mcq);

        } catch (Exception e) {
            System.out.println("error on saving data to json" + e.getMessage());
        }

    }
    
    
    public void updateMCQ()
    {
       curd updateMCQ = new curd();  
       updateMCQ.update("c2f597c9-8eef-4288-9aeb-e43f180b97ec", "mcqQuestion");
    }
    
    public void deleteMCQ()
    {
       curd deleteMCQ = new curd();  
       deleteMCQ.delete("bece6676-734f-4449-b125-355b1c3b2075", "mcqQuestion");
    }
}
