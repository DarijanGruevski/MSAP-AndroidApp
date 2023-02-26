using System.ComponentModel.DataAnnotations;

namespace AppReminderAPI.Models
{
    public class Reminder
    {
        public int Id { get; set; }

        public string Note { get; set; }

    
        public string Date { get; set; }

       
        public string Time { get; set; }


    }

}
