namespace ExtensionMethodPractice;

class Program
{
    static void Main(string[] args)
    {
        string str1 = "Hello world";
        Console.WriteLine(str1.ToPigLatin());

        string bonus = "I just really wanted to test the pig latin";
        Console.WriteLine(bonus.ToPigLatin());
        
        string str2 = "This is a test sentence";
        Console.WriteLine($"The word count is {str2.WordCount()}");

        string str3 = "c# is fun";
        Console.WriteLine(str3.ReverseWord());

        string str4 = "A man a plan a canal Panama";
        Console.WriteLine(str4.IsPalindrome());

        string str5 = "I really like PIE!";
        Console.WriteLine(str5.FlipCasing());
    }
}