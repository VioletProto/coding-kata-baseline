using System.Runtime.InteropServices.ComTypes;
using System.Text;

namespace ExtensionMethodPractice
{
    public static class StringExtensions
    {
        public static string ToPigLatin(this string str)
        {
            string[] words = str.Split(' ');
            StringBuilder sb = new StringBuilder();

            foreach (string word in words)
            {
                if (string.IsNullOrEmpty(word))
                {
                    sb.Append(word + " ");
                    continue;
                }

                string pigLatinWord;

                if ("aeiouAEIOU".Contains(word[0]))
                {
                    pigLatinWord = word + "way";
                }
                else
                {
                    int vowelIndex = word.IndexOfAny("aeiouAEIOU".ToCharArray());

                    if (vowelIndex == -1)
                    {
                        pigLatinWord = word + "ay";
                    }
                    else
                    {
                        pigLatinWord = word.Substring(vowelIndex) + word.Substring(0, vowelIndex) + "ay";
                    }
                }

                sb.Append(pigLatinWord + " ");
            }

            return sb.ToString().TrimEnd();
        }

        public static int WordCount(this string str)
        {
            string[]  words = str.Split(' ');
            return words.Length;
        }

        public static string ReverseWord(this string str)
        {
            string[]  words = str.Split(' ');
            Array.Reverse(words);
            return string.Join(" ", words);
        }

        public static bool IsPalindrome(this string str)
        {
            string cleaned = new string(str.Where(char.IsLetterOrDigit).ToArray()).ToLower();
            string reversed = new string(cleaned.Reverse().ToArray());
            return cleaned == reversed;
        }

        public static string FlipCasing(this string str)
        {
            return new string(str.Select(c => char.IsUpper(c) ? char.ToLower(c) : char.ToUpper(c)).ToArray());
        }
    }
}

