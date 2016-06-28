using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.IO;

namespace BMI
{
    class Menu
    {
        public void header()
        {
            int x = 18;
            int y = 10;
            Console.ForegroundColor = ConsoleColor.White;
            Console.SetCursorPosition(x - 18, y - 8);
            Console.WriteLine("==================================================");
            Console.SetCursorPosition(x - 18, y - 7);
            Console.WriteLine("==================================================");
            Console.ForegroundColor = ConsoleColor.White;
            Console.SetCursorPosition(x, y - 5);
            Console.WriteLine("BODY MASS INDEX");
            Console.SetCursorPosition(x - 18, y - 2);
            Console.WriteLine("==================================================");
            Console.SetCursorPosition(x - 18, y - 3);
            Console.WriteLine("==================================================");
        }

        public void isi()
        {

            Console.Clear();
            FileStream fs = new FileStream("user.txt", FileMode.Open, FileAccess.Read);
            StreamReader sr = new StreamReader(fs);

            string line = sr.ReadLine();
            char[] delim = { '#' };
            int flag = 0; //menandakan data ada/tidak

            while (line != null)
            {
                string[] isi = line.Split(delim);
                string unik = isi[0];
                string nama = isi[1];
                string id = isi[2];
                string pass = isi[3];
                DateTime lahir = DateTime.Parse(isi[4]);


                Console.Clear();
                header();
                Console.SetCursorPosition(4, 10);
                Console.Write("Insert Height\t: ");
                int tinggi = Convert.ToInt16(Console.ReadLine());


                Console.SetCursorPosition(4, 12);
                Console.Write("Insert Weight\t: ");
                int berat = Convert.ToInt16(Console.ReadLine());

                //mengambil nama depan saja
                int PosisiSpasi = isi[0].IndexOf(' ');
                Console.ForegroundColor = ConsoleColor.Yellow;
                string namaDepan = PosisiSpasi > 0 ? isi[1].Substring(0, PosisiSpasi) : isi[1];
                //kalkulasi umur dan berat badan ideal
                DateTime sekarang = DateTime.Today;
                int umur = sekarang.Year - lahir.Year;
                //hitung berat badan ideal dengan rumus BBI = (TB - 100) x 90%
                float bbi = (tinggi - 100) * 0.9f;

                //hitung bmi (body mass index) dengan rumus
                //BMI = (BB) / [(TB) * (TB)]
                //keterangan
                //BMI < 18.5 = berat badan kurang (underweight)
                //BMI 18.5 - 24 = normal
                //BMI 25 - 29 = kelebihan berat badan (overweight)
                //BMI >30 = obesitas
                float tinggiMeter = (float)tinggi / 100;
                float bmi = berat / (tinggiMeter * tinggiMeter);
                string ketBerat;
                if (bmi < 18.5)
                {
                    ketBerat = "Thin";
                }
                else if (bmi >= 18.5 && bmi < 25)
                {
                    ketBerat = "Normal";
                }
                else if (bmi >= 25 && bmi < 30)
                {
                    ketBerat = "Over Weight";
                }
                else
                {
                    ketBerat = "Obbesity";
                }

                FileStream fst = new FileStream("data.txt", FileMode.Append, FileAccess.Write);
                StreamWriter sw = new StreamWriter(fst);

                string gabung = unik + "#" + DateTime.Now + "#" + tinggi + "#" + berat + "#" + bbi;

                sw.WriteLine(gabung);

                sw.Flush();
                sw.Close();
                fst.Close();

                Console.Clear();
                header();
                Console.SetCursorPosition(4, 11);
                Console.WriteLine("               Hello");
                Console.WriteLine("   In your age now, Your Ideal weight is " + bbi + " kg");
                Console.WriteLine("         and your weight status is " + ketBerat);
                Console.ReadLine();

                Console.Clear();
                header();
                ceklagi();



                fs.Close();


            }
        }

        public void ceklagi()
        {
            header();
            Console.SetCursorPosition(4,11);
            Console.Write("  Do You Want Another Check ? (y/n) ");
            string jawab = Console.ReadLine();

            switch (jawab)
            {
                case "y":
                    isi();
                    break;
                case "n":
                    Cover cv = new Cover();
                    cv.tamp();
                    break;
                default:
                    break;
            }
        }
    }
}
