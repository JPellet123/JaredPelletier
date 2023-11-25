using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Linq;

namespace PelletierOOPLab3
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

        }

        /* Name: Jared Pelletier
         * Date: Jan 25 2023
         * Description: Adding values to a listbox using multiple classes and saving results to external
         * output file.*/

        /*Method: ResetForm
        //Sent: None 
        //Return: None
        //Description: Reset all text boxes and place cursor on top textbox
        */
        private void ResetForm()
        {
            txtName.Text = "";
            txtPrice.Text = "";
            txtRating.Text = "";
            txtName.Focus();
        }

        /*Method: CheckRating
        //Sent: String rating 
        //Return: None
        //Description: Create an array containing the ratings, use foreach loop to iterate
        through and validate the rating that was attempted to be given
        */  

        private void Form1_Load(object sender, EventArgs e)
        {
            //call reset form
            ResetForm();

            //creating new game object called myGame
            Game myGame= new Game();

            //set values of myGame using properties
            myGame.Name = "Elden Ring";
            myGame.Price = 189.20m;

            string rating = "hello";
            //call checkrating sending in invalid rating
            //CheckRating(ref rating);

            //set rating to the returned string 
            myGame.Rating = rating;

            //call displaygame using mygame
            lstGames.Items.Add(myGame.DisplayGame());

            //clear - call reset form
            ResetForm();
        } //end of form load 

        private void btnClear_Click(object sender, EventArgs e)
        {
            ResetForm();
        }//end of btnclear 


        private void btnAdd_Click(object sender, EventArgs e)
        {
            bool isDecimal;
            decimal test = 0m;
            string rating = txtRating.Text;
            string name = txtName.Text;


            if (decimal.TryParse(txtPrice.Text, out test))
            {
                isDecimal = true;
            }
            else
            {
                isDecimal = false;
            }

            if (txtName.Text.Length >= 5 && isDecimal && txtRating.Text != "")
            {
                //call check rating sending in validated rating
                //CheckRating(ref rating);

                //creating new game object called yourGame
                Game yourGame = new Game(name, test, rating);                

                //change price to be double of whatever was entered (dont hardcode)
                decimal multi = 2.0m;
                yourGame.Price = yourGame.Price * multi;

                //call displaygame method sending in yourGame data                
                lstGames.Items.Add(yourGame.DisplayGame());
            }//end of if statement
        }//end of btnadd click

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (lstGames.Items.Count != 0)
            {
                DialogResult userSelection = sfdOutput.ShowDialog();

                if (userSelection == DialogResult.Cancel)
                {
                    MessageBox.Show("File not Saved", "Cancel Selected on Save");
                }
                else
                {
                    StreamWriter outputFile = new StreamWriter(sfdOutput.FileName);
                    outputFile.WriteLine("Jared Pelletier");
                    for (int i = 0; i < lstGames.Items.Count; i++)
                    {
                       outputFile.WriteLine(lstGames.Items[i]);
                    }
                        
                    outputFile.Close();

                    string dir = @"c:\files\PelletierOOPLab3.txt";
                    MessageBox.Show( "File saved to " + dir, "Confirm Save");
                }

            }
            

        }
    }//end of form1 class

}
