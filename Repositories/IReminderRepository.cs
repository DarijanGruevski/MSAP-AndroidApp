using AppReminderAPI.Models;

namespace AppReminderAPI.Repositories
{
    public interface IReminderRepository
    {

        Task<IEnumerable<Reminder>> Get();

        Task<Reminder> Get(int id);

        Task Update(Reminder Reminder);

        Task Delete(int id);

        Task<Reminder> Create(Reminder Reminder);
    }
}
