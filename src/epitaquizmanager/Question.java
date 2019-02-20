/* 
**********************
Authors: Loord Dinsan
Company: EPITA
Date   : 12/02/2019
**********************
*/
package epitaquizmanager;


public abstract class Question {

 /**
  * Default constructor
  */
 public Question() {}


 private String ID;
 private String question;
 private String[] topics;
 private String difficulty;

 
 
 public void seTopics(String[] topics) {
  this.topics = topics;
 }

 public void setDifficulty(String difficulty) {
  this.difficulty = difficulty;
 }

 public void setQuestion(String question) {
  this.question = question;
 }

  public void setID(String ID) {
  this.ID = ID;
  
 }
   
}