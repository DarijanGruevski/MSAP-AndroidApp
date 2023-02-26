using AppReminderAPI.Data;
using AppReminderAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace AppReminderAPI.Repositories
{
    public class ReminderRepository : IReminderRepository
    {
        private readonly DataReminderContext _context;
        public ReminderRepository(DataReminderContext context)
        {
           _context = context;
        }

        public async Task<Reminder> Create(Reminder Reminder)
        {
            _context.Reminders.Add(Reminder);
            await _context.SaveChangesAsync();
            return Reminder;
        }

        public async Task Delete(int id)
        {
           var ReminderToDelete =  await _context.Reminders.FindAsync(id);
            _context.Reminders.Remove(ReminderToDelete);
            await _context.SaveChangesAsync();
        }

        public async Task<IEnumerable<Reminder>> Get()
        {
            return await _context.Reminders.ToListAsync();
        }

        public async Task<Reminder> Get(int id) 
        {
            return await _context.Reminders.FindAsync(id);
        }

        public async Task Update(Reminder Reminder)
        {
            _context.Entry(Reminder).State = EntityState.Modified;
             await _context.SaveChangesAsync();
        }
    }
}
