import sys
from random import *
import collections

#delivery_address(house_num, street_name, city, state, country_code, zip_code, cust_email)
house_num = ["123","432","42","65","765","9","7","65","89","64","90","23","345"]
street_name = ["Park Avenue", "West Boulevard", "John Street", "Jefferson Road", "Boardwalk Terrace"]
city = ["Greece", "Henrietta", "Syracuse", "Rochester", "Auburn", "Buffalo"]
state = ["Idaho", "Wyoming", "Texas", "New Jersey", "New York", "Georgia"] 
country_code = ["US", "CA", "ME"]
zip_code = ["48935", "15643", "68791", "65423", "48931", "12935", "32164", "98765", "32476"] 

#customer(email, display_name, password, house_number, street_name, city_name, state_name, phone_number, day, month, year)
email = [ "frederic@yahoo.com",
"stinson@aol.com",
"cgcra@verizon.net",
"meinkej@att.net",
"jmorris@outlook.com",
"nachbaur@live.com",
"zeitlin@aol.com",
"wildfire@gmail.com",
"jpflip@icloud.com",
"trygstad@outlook.com",
"monopole@hotmail.com",
"arnold@comcast.net",
"nogin@yahoo.ca",
"paulv@me.com",
"duchamp@live.com",
"novanet@icloud.com",
"skaufman@optonline.net",
"multiplx@me.com",
"birddog@att.net",
"aukjan@outlook.com"]

display_name = [ "frederic",
"stinson",
"cgcra",
"meinkej",
"jmorris",
"nachbaur",
"zeitlin",
"wildfire",
"jpflip",
"trygstad",
"monopole",
"arnold",
"nogin",
"paulv",
"duchamp",
"novanet",
"skaufman",
"multiplx",
"birddog",
"aukjan"]


def pick_rand(arr):
    return arr[randint(0, len(arr)-1)]


def main():
    f = open("delivery_address.txt", "w")
    for i in range(0, 20):
        f.write(pick_rand(house_num))
        f.write(",")
        f.write(pick_rand(street_name))
        f.write(",")
        f.write(pick_rand(city))
        f.write(",")
        f.write(pick_rand(state))
        f.write(",")
        f.write(pick_rand(country_code))
        f.write(",")
        f.write(pick_rand(zip_code))
        f.write("\n")

    f = open("customer.txt", "w")
    for i in range(0, 20):
        f.write(email[i])
        f.write(",")
        f.write(display_name[i])
        f.write(",")
        f.write(pick_rand(house_num))
        f.write(",")
        f.write(pick_rand(house_num))
        f.write(",")
        f.write(pick_rand(house_num))
        f.write(",")
        f.write(pick_rand(house_num))
        f.write(",")
        f.write(pick_rand(house_num))
        f.write(",")
        f.write(pick_rand(house_num))
        f.write(",")
        f.write(pick_rand(house_num))
        f.write(",")
        f.write(pick_rand(house_num))
        f.write(",")
        f.write(pick_rand(house_num))
        f.write("\n")




if __name__ == "__main__":
    main()
