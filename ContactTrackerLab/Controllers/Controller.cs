using DavianHernandez_CosoleIO;

namespace ContactTrackerLab.Controllers;

public class Controller
{
    public List<Contact> contacts;
    DataController dController = new DataController();
    
    public void Run()
    {
        IEnumerable<string> options = new[] { 
            "Show All Contacts"
            , "Add a Contact"
            , "Update a Contact"
            , "Find a Contact"
            , "Delete a Contact"
        };
        do
        {
            int userChoice = ConsoleIO.PromptForMenuSelection(options, true);

            contacts = dController.Load();

            int choice = userChoice;

            switch (choice)
            {
                case 0:
                    Console.WriteLine("Goodbye");
                    return;
                case 1:
                    showAllContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    findContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                default:
                    throw new ArgumentOutOfRangeException("Something went wrong");
            }
        } while (true);
    }

    public void showAllContacts()
    {
        foreach (Contact contact in contacts)
        {
            Console.WriteLine(contact.ToFormattedString());
        }

        Console.WriteLine($"There are {contacts.Count} contacts");
    }

    public void addContact()
    {
        Contact contact = new Contact();
        contact.Name = ConsoleIO.PromptForInput("Please enter the name of the contact.", false);
        contact.Age = ConsoleIO.PromptForInt("Please enter the age of the contact.", 0, 122);
        contact.PhoneNumber =
            ConsoleIO.PromptForInput("Please enter the phone number of the contact.", false);
        dController.SaveOne(contact);
        Console.WriteLine("Contact added");
    }

    public void updateContact()
    {
        Contact contact = new Contact();
        do
        {
            contact.Id = ConsoleIO.PromptForInt("Please enter the id of the contact you want to update."
                , 1, int.MaxValue);
            contact.Name = ConsoleIO.PromptForInput("Please enter the name of the contact.", false);
            contact.Age = ConsoleIO.PromptForInt("Please enter the age of the contact.", 0, 122);
            contact.PhoneNumber = ConsoleIO.PromptForInput("Please enter the phone number of the contact.", false);
            try
            {
                dController.UpdateContact(contact);
                Console.WriteLine("Contact updated");
                return;
            }
            catch (KeyNotFoundException e)
            {
                Console.WriteLine("please enter a valid Id");
            }
            catch (FileLoadException ex)
            {
                Console.WriteLine("File was empty, please enter a contact to update.");
            }
        } while (true);
    }

    public void findContact()
    {
        IEnumerable<string> options = new[] { "Search by Id", "Search by name" };
        int choice = ConsoleIO.PromptForMenuSelection(options, true);

        switch (choice)
        {
            case 0:
                return;
            case 1:
                do
                {
                    int id = ConsoleIO.PromptForInt("Please enter the Id you would like to find.", 1, int.MaxValue);
                    try
                    {
                        Console.WriteLine(dController.GetById(id).ToFormattedString());
                        return;
                    }
                    catch (KeyNotFoundException e)
                    {
                        Console.WriteLine("User not found, please enter a valid Id");
                    }
                    catch (FileLoadException e)
                    {
                        Console.WriteLine("File was empty, please enter a contact to find a contact.");
                    }
                } while (true);
            case 2:
                do
                {
                    string name =
                        ConsoleIO.PromptForInput("Please enter the name of the contact you would like to find.", false);
                    try
                    {
                        var contacts = dController.GetByName(name);

                        foreach (Contact contact in contacts)
                        {
                            Console.WriteLine(contact.ToFormattedString());
                        }
                        
                        return;
                    }
                    catch (KeyNotFoundException e)
                    {
                        Console.WriteLine("User not found, please enter a valid Name");
                    }
                    catch (FileLoadException e)
                    {
                        Console.WriteLine("File was empty, please enter a contact to find a contact.");
                    }
                }while (true);
        }
    }

    public void deleteContact()
    {
        do
        {
            
            int id = ConsoleIO.PromptForInt("Enter the Id of the person you would like to remove from your contacts", 1, int.MaxValue);
            try
            {
                dController.DeleteContact(id);
                Console.WriteLine("Contact removed");
                return;
            }
            catch (KeyNotFoundException e)
            {
                Console.WriteLine("User not found, please enter a valid Id");
            }
            catch (FileLoadException e)
            {
                Console.WriteLine("File was empty, please enter a contact before you can delete a contact.");
            }
        }while (true);
    }
    
}