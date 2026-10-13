public class Contact
{
    private int _id;
    private string name;
    private int age;
    private string phoneNumber;

    public int Id
    {
        get { return _id; }
        set { _id = value; }
    }

    public string Name
    {
        get { return name; }
        set { name = value; }
    }
    
    public int Age 
    {
        get { return age; }
        set { age = value; }
    }
    
    public string PhoneNumber
    {
        get { return phoneNumber; }
        set { phoneNumber = value; }
    }

    public override string ToString()
    {
        return String.Format("{0}, {1}, {2}, {3}", _id, name, age, phoneNumber);
    }
}