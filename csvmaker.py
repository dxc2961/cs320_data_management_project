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
password = ["VBIZC4i3Qe", 
"5ZNbCjbfm0",
"OQgTMAntLl",
"DR4L1xABeV",
"wBEt0LatrA",
"zcyuIKiT9X",
"qMy6Xa0gIv",
"DLRoUEDmhY",
"nuVlQQ6WMP",
"W95nZYzkGH",
"UqLnRdW8lA",
"lnPKFzRado",
"PPdmDbznuS",
"XH4TQ3IQFc",
"MxzJpqsjVH",
"ujQoAUZe0j",
"uIHMSY31vE",
"0yWA1poAvk",
"J25wPPPPyR",
"iq0QSgX9q9"]
phone_number = ["(251)923-8150", "(617)267-5691",  "(602)954-1251",  "(650)631-7238",  "(430)625-6447",  "(443)782-2635",  "(504)770-1678",  "(516)797-3606",  "(206)567-7521",  "(414)871-9069",  "(343)400-2043",  "(484)934-7879",  "(616)337-4809",  "(716)213-6837",  "(657)214-3685",  "(405)853-9853",  "(225)212-2652",  "(443)476-8349",  "(303)557-4107",  "(443)815-7185"] 
month = ["January", 
"February",
"March",
"April",
"May",
"June",
"July",
"August",
"September",
"October",
"November",
"December"]
year = ["1958", 
"1951",
"1963",
"1961",
"1984",
"1998",
"1982",
"1969",
"1967",
"1974"]

#package(order_id, package_id, package_type, weight, 
#delivery_speed, current_status, sign_required_status, 
#insurance_level, hazard_status, fragile_status, perishable_status, 
#item_description, provided_value, international_fee)
package_type = ["envelope", "box", "bag", "tube"]
delivery_speed = ["standard", "express", "overnight", "no_rush"]
current_status = ["in_warehouse", "in_transit", "delivered"]
sign_req = ["yes", "no", "no", "no", "no"]
insurance = ["yes", "no", "no", "no", "no"]
hazard = ["yes", "no", "no", "no", "no"]
fragile = ["yes", "no", "no", "no", "no"]
perishable = ["yes", "no", "no", "no", "no"]
item_description = ["texbook", "calculator", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""]
provided_value = ["199", "100", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""]

#order(order_id, payment_id, email, order_date, delivery_date, delivery_address_id)
#order_id sequential
#payment_id sequential
#email sequential
#order date rand between 1 and 31 then concat with:
order_date = "-1-2019"
delivery_date = "-3-2019"
#delivery address_id sequential

#payment_method(payment_id, cust_email)

#gift_card(gift_card_id, payment_id, expiration_date, security_code, balance)
gift_card_id = ["4vXH4yeNooHWsDh",
"4zeETsa1eyz5VFU",
"5gEsXqaLr5woWmv",
"IvvbhBPRasJQCyh",
"BDGzzAdxYnCKomr",
"6DI4JqcI12BBPe7",
"FMBfi3VvIOimLk7",
"9Q2FYi8euY1KdqO",
"2egYWcihOU7WZti",
"YcETgJIKxkSdycr",
"dk9x4Yy4EieNCKz",
"tihWlli84gnDIX4",
"5i3D9Ap7ELqQKna",
"PDgNENZWbBJCk5X",
"MMxhkXCG0ysuA0u",
"pD75o7dV9n0V2xy",
"U61tNgPgMLjc4vA",
"fu3yv6SMBZpOzAZ",
"RnX5B4ZSCXZpy7f",
"C8WXX9Kyj5SfzB5"]
payment_id2 = ["2", "8", "12", "14", "18", "20", "35", "56", "98", "78", "45", "78", "214", "49", "57", "68", "89", "78", "54", "120"] 
expiration_date = "-1-2025"
balance = ["10", "25", "50", "100"]


#check(routing_number, account_number, check_number, payment_id)
routing_number = ["312950389", 
"764937450",
"361936529",
"388474715",
"243126683",
"230431852",
"399506609",
"928253576",
"941589891",
"610452548",
"499471031",
"559405010",
"201200098",
"282582058",
"831962278",
"473430533",
"411106840",
"388204559",
"397108877",
"273649328"]
account_number = ["598541",
"370264",
"535260",
"165600",
"626243",
"349088",
"448575",
"790881",
"519518",
"265341",
"935393",
"518414",
"990871",
"763803",
"495183",
"054659",
"620860",
"628640",
"436716",
"759996"]
check_number = ["9569"
"7879",
"9267",
"1387",
"4080",
"6271",
"2147",
"6268",
"2462",
"2096",
"7205",
"1828",
"6385",
"8044",
"8576",
"1548",
"2235",
"8609",
"6365",
"9675",
"2172"]
payment_id3 = ["4", "6", "10", "16", "654", "97", "78", "813", "46", "37", "218", "419", "37", "28", "38", "39", "35", "65", "34", "28"] 

#credit_card(card_number, payment_id, owner_name, expiration_date, security_code)
card_number = ["1529254915577471",
"5239461171549961",
"6900563282860639",
"4455716932449885",
"8609462428272953",
"0270780141381795",
"2118220676982916",
"1659298050887927",
"5342522077422254",
"8596008278627984",
"2503492304572582",
"7187213658321705",
"9037359667032830",
"2143578028574582",
"3757335946109218",
"6424353587694576",
"8282045120102156",
"0211730929319552",
"2093264814753844",
"5599088497336713"]
payment_id4 = ["1", "3", "5", "7", "9", "11", "13", "15", "17", "19", "654", "31", "78", "35", "78", "86", "54", "74", "979", "54"] 
owner_name = ["Quincy Tolly", 
"Clarice Ashlynn",
"Kristi Mayson",
"Wanda Annora",
"Giselle Kaila",
"Amias Justy",
"Janie Seward",
"Wynne Rylie",
"Tawny Sherley",
"Kieron Marlon",
"Darion Sherisse",
"Ramona Acacia",
"Iris Leontyne",
"Kenrick Melanie",
"Mabelle Kathleen",
"Kristel Kierra",
"Tallulah Jakki",
"Pheobe Rebeckah",
"Hamnet Shania",
"Sandy Frank"]
#exp date is rand 1-12 / rand 20-25
security_code = ["137",
"621",
"369",
"532",
"318",
"903",
"734",
"329",
"136",
"975",
"493",
"014",
"362",
"697",
"079",
"611",
"116",
"921",
"720",
"592"]





def pick_rand(arr):
    return arr[randint(0, len(arr)-1)]


def main():
    f = open("delivery_address.csv", "w")
    f.write("address_id,house_num,street_name,city,state,country_code,zip_code,cust_email\n")
    for i in range(0, 20):
        f.write(str(i+1) + ",")
        f.write(pick_rand(house_num) + ",")
        f.write(pick_rand(street_name) + ",")
        f.write(pick_rand(city) + ",")
        f.write(pick_rand(state) + ",")
        f.write("US" + ",")
        f.write(pick_rand(zip_code) + ",")
        f.write(email[i] + "\n")

    f = open("customer.csv", "w")
    f.write("email,display_name,password,home_address_id,phone_number,day,month,year\n")
    for i in range(0, 20):
        f.write(email[i] + ",")
        f.write(display_name[i] + ",")
        f.write(password[i] + ",")
        f.write(str(i+1) + ",")
        f.write(phone_number[i] + ",")
        f.write(str(randint(1, 28)) + ",")
        f.write(pick_rand(month) + ",")
        f.write(pick_rand(year) + "\n")

    f = open("package.csv", "w")
    f.write("order_id,package_id,package_type,weight,delivery_speed,current_status,sign_required_status,insurance_level,hazard_status,fragile_status,perishable_status,item_description,provided_value\n")
    for i in range (0,20):
        f.write(str(i+1) + ",")
        f.write(str(i+125) + ",")
        f.write(pick_rand(package_type) + ",")
        f.write(str(randint(1,50)) + ",")
        f.write(pick_rand(delivery_speed) + ",")
        f.write(pick_rand(current_status) + ",")
        f.write(pick_rand(sign_req) + ",")
        f.write(pick_rand(insurance) + ",")
        f.write(pick_rand(hazard) + ",")
        f.write(pick_rand(fragile) + ",")
        f.write(pick_rand(perishable) + ",")
        f.write(item_description[i] + ",")
        f.write(provided_value[i] + "\n")

    f = open("order.csv", "w")
    f.write("order_id,payment_id,email,order_date,delivery_date,delivery_address_id\n")
    for i in range (0,20):
        f.write(str(i+1) + ",")
        f.write(str(i+1) + ",")
        f.write(email[i] + ",")
        f.write(str(randint(1, 31)) + order_date + ",")
        f.write(str(randint(1, 31)) + delivery_date + ",")
        f.write(str(i+1) + "\n")

    f = open("payment.csv", "w")
    f.write("payment_id,cust_email\n")
    for i in range (0,20):
        f.write(str(i+1) + ",")
        f.write(email[i] + "\n")

    f = open("giftcard.csv", "w")
    f.write("gift_card_id,payment_id,expiration_date,security_code,balance\n")
    for i in range (0,20):
        f.write(gift_card_id[i] + ",")
        f.write(payment_id2[i] + ",")
        f.write(str(randint(1, 31)) + expiration_date + ",")
        f.write(pick_rand(balance) + "\n")

    f = open("check.csv", "w")
    f.write("routing_number,account_number,check_number,payment_id\n")
    for i in range (0,20):
        f.write(routing_number[i] + ",")
        f.write(account_number[i] + ",")
        f.write(check_number[i] + ",")
        f.write(payment_id3[i] + "\n")

    f = open("creditcard.csv", "w")
    f.write("card_number,payment_id,owner_name,expiration_date,security_code\n")
    for i in range (0,20):
        f.write(card_number[i] + ",")
        f.write(payment_id4[i] + ",")
        f.write(owner_name[i] + ",")
        f.write(str(randint(1, 12)) + "/" + str(randint(20, 25)) + ",")
        f.write(security_code[i] + "\n")

if __name__ == "__main__":
    main()
