namespace ContactTrackerLab;

public static class ContactExtensions
{
    public static string ToFormattedString(this Contact contact)
    {
        if (contact == null)
            return "No contact information available";

        return $"---------------------------------\n" +
               $"ID: {contact.Id}\n" +
               $"Name: {contact.Name}\n" +
               $"Age: {contact.Age}\n" +
               $"Phone: {contact.PhoneNumber}";
    }
}