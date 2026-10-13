namespace SecondCSharpSolution.Model;

public class Cat
{
    public int Id { get; set; }
    public string Name { get; set; }
    public string Color { get; set; }
    public int Weight { get; set; }
    
    public void Meow()
    {
        Console.WriteLine($"{this.Name} Meow");
    }
}