using System.Text.Json;

namespace ContactTrackerLab.Controllers;

public class DataController
{
    private readonly string _filePath;
    private readonly JsonSerializerOptions _options;

    public DataController()
    {
        string baseDirectory = AppDomain.CurrentDomain.BaseDirectory;
        string projectDirectory = Directory.GetParent(baseDirectory).Parent.Parent.Parent.FullName;
        
        _filePath = Path.Combine(projectDirectory, "ContactsData.json");
        
        _options = new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
            WriteIndented = true
        };
    }

    public List<Contact> Load()
    {
        try
        {
            if (!FileExists())
            {
                Console.WriteLine($"File not found: {GetFilePath()}");
                return null;
            }
            var contactsJson = File.ReadAllText(_filePath);
            
            return JsonSerializer.Deserialize<List<Contact>>(contactsJson, _options);
        }
        catch (Exception e)
        {
            Console.WriteLine($"error loading file: {e.Message}"); 
        }

        return null;
    }

    public void SaveOne(Contact contact)
    {
        var list = Load();
        
        if (list == null)
            list = new List<Contact>();

        contact.Id = GetNextAvailableId();
        list.Add(contact);
        
        File.WriteAllText(_filePath, JsonSerializer.Serialize(list, _options));
    }

    public int GetNextAvailableId()
    {
        var list = Load();
        if (list == null || list.Count == 0)
            return 1;
        
        var existingIds = new HashSet<int>(list.Select(c => c.Id));

        int id = 1;
        while (existingIds.Contains(id))
        {
            id++;
        }
        
        return id;
    }

    public void UpdateContact(Contact contact)
    {
        var list = Load();

        if (list == null || list.Count == 0)
            throw new FileLoadException("No contacts found");
        
        int index = list.Where(c => c.Id == contact.Id).First().Id;

        if (index == -1)
            throw new KeyNotFoundException($"Contact with id {contact.Id} not found");
        
        list[index] = contact;
        
        File.WriteAllText(_filePath, JsonSerializer.Serialize(list, _options));
    }

    public Contact GetById(int id)
    {
        var list = Load();
        
        if (list == null || list.Count == 0)
            throw new FileLoadException("No contacts found");
        
        int index = list.Where(c => c.Id == id).First().Id;
        
        if (index == -1)
            throw new KeyNotFoundException($"Contact with id {id} not found");
        
        return list[index];
        
    }

    public List<Contact> GetByName(string name)
    {
        var list = Load();
        
        if (list == null || list.Count == 0)
            throw new FileLoadException("No contacts found");
        
        var matches = list.Where(c => c.Name.Contains(name, StringComparison.OrdinalIgnoreCase)).ToList();
        
        if (matches.Count == 0)
            throw new KeyNotFoundException($"Contact with name {name} not found");
        
        return matches;
    }

    public void DeleteContact(int id)
    {
        var list = Load();
        if (list == null || list.Count == 0)
            throw new FileLoadException("No contacts found");
        
        int index = list.Where(c => c.Id == id).First().Id;
        if (index == -1)
            throw new KeyNotFoundException($"Contact with id {id} not found");
        list.RemoveAt(index);
        File.WriteAllText(_filePath, JsonSerializer.Serialize(list, _options));
    }

    public bool FileExists()
    {
        return File.Exists(_filePath);
    }
    
    public string GetFilePath()
    {
        return _filePath;
    }
}

