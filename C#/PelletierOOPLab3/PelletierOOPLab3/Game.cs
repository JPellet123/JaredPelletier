using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PelletierOOPLab3
{
    public class Game
    {
        //setting the fields
        private string name;
        private decimal price;
        private string rating;

        //setting the properties
        public string Name
        {
            get { return name; }
            set { name = value; }
            
        }
        public decimal Price
        {
            get { return price; }
            set { price = value; }
        }

        public string Rating
        {
                      
            get { return rating; }
            set 
            {
                CheckRating(ref value);
                rating = value;
                
            } 
        }

        //creating methods 

        //using default constructor
        public Game() {}

        //using custom constructor
        public Game(string inName, decimal inPrice, string inRating)
        {
            Name = inName;
            Price = inPrice;
            Rating = inRating;
        }

        //using lambda and custom constructor
        public string DisplayGame()=>
            Name + ", " + Price.ToString("c") + ", " + Rating;

        private void CheckRating(ref string rating)
        {
            //create a new array containing the valid ratings
            string[] ratingArray;
            ratingArray = new string[5];
            ratingArray[0] = "EVERYONE";
            ratingArray[1] = "10+";
            ratingArray[2] = "TEEN";
            ratingArray[3] = "MATURE";
            ratingArray[4] = "ADULT";

            //convert string sent in to uppercase
            rating = rating.ToUpper();


            int counter = 0;

            //use foreach loop to validate string sent vs known valid ratings
            foreach (string str in ratingArray)
            {
                if (rating != str)
                {
                    counter++;
                }                
            }//end of foreach

            if (counter == ratingArray.Length)
            {
                rating = "UNKNOWN";
            }//end of if
        }//end of checkrating 









    }
}
