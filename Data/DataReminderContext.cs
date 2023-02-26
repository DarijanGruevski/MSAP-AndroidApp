using AppReminderAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace AppReminderAPI.Data
{
    public class DataReminderContext : DbContext
    {
        public DataReminderContext(DbContextOptions<DataReminderContext> options) : base(options)
        {
            Database.EnsureCreated();
        }

        public DbSet<Reminder> Reminders { get; set; }
    }
}
