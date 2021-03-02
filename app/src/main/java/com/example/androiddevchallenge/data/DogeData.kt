package com.example.androiddevchallenge.data

data class DogeData(
    val url: String,
    val name: String,
    val desc: String,
)

private val mUrls = arrayListOf("https://www.dogtime.com/assets/uploads/2016/04/homemade-dog-bed.jpg",
"https://www.dogtime.com/assets/uploads/2019/01/dogpaw1.jpg", "https://www.dogtime.com/assets/uploads/2017/10/hydronephrosis-dogs-1.jpg",
"https://www.dogtime.com/assets/uploads/2008/04/small-not-yappy-dog-breeds-6.jpg", "https://www.dogtime.com/assets/uploads/2009/05/dachshund-dog-names-e1580146228378.jpg",
"https://www.dogtime.com/assets/uploads/2019/01/dogbrush1.jpg")

private val mTitles = arrayListOf("National Craft Month: Make A DIY Dog Bed With An Old Toy Box Or Chest",
    "How To Safely Take Paw Prints Of Your Dog’s Paws", "Hydronephrosis In Dogs: Symptoms, Causes, & Treatments",
    "13 Small, Mostly Quiet Dog Breeds That Aren’t Yappy", "30 Best Dog Names For Dazzling Dachshunds", "Dog Brushes: Which Type Is Best For Your Dog’s Coat?")

private val mDesc = arrayListOf("March is National Craft Month! It’s a time to get artsy and use our creativity. If you’ve got an old toy box or chest lying around, now is a good time to recycle it. Make it into a fun DIY dog bed where your pooch can get some rest and relaxation.",
"You can use paw prints of your favorite pooch’s feet for art projects, memorials, or tributes, and they can be a lot of fun. Here’s how you can take doggy paw prints safely.",
"Hydronephrosis in dogs happens when one or both kidneys are swollen due to a buildup of urine, which happens when fluid can’t properly drain from the kidney to the bladder for release. This can occur due to a variety of reasons, including kidney obstruction, kidney stones, tumors, disease, or injury.",
"You can have it both ways: Check out this list of small dog breeds who aren’t yappy. If you’re looking for a little dog without the yappy barks, whines, and howls, then check out these small, mostly quiet dog breeds!",
"Are you looking for the best dog names for Dachshunds? Here are some of the best dog names from fellow Doxie lovers on Instagram along with cute pictures to give you some dog name inspiration for your Dachshund pal!",
"You might not think of it, but depending on your dog’s fur, you will likely need a specific kind of brush. There are many different kinds of dog brushes for many different types of coats, and it can be tough to know how to pick the right one. Here’s a quick guide.")

fun generateDogeData(): ArrayList<DogeData> {
    val arrayList = arrayListOf<DogeData>()
    for (i in 0 until mUrls.size) {
        arrayList.add(DogeData(mUrls[i], mTitles[i], mDesc[i]))
    }
    return arrayList
}

fun generateBannerData(): ArrayList<DogeData> {
    val firstBanner = DogeData(
        url = "https://www.dogtime.com/assets/uploads/2018/02/national-craft-month-diy-dog-sweater-1.jpg",
        name = "National Craft Month: Try Making A DIY Dog Sweater",
        desc = "March is National Craft Month — a time to get creative and try your hand at making new things. Our dogs provide no shortage of inspiration for us to get artsy. Why not use that inspiration to make something practical, like a do-it-yourself dog sweater?\n" +
                "\n" +
                "After all, the weather is still a little chilly in March, and your pup would look oh-so-cute in a sweater made by mommy or daddy."
    )
    val secondBanenr = DogeData(
        "https://www.dogtime.com/assets/uploads/2015/08/dog-poison-chocolate-e1552081331319.jpg",
        "10 Of The Most Common Ways Dogs Are Accidentally Poisoned",
        "We all think that we know the basics of keeping our pets safe, yet each year there are thousands of pet poisoning cases just in the United States.\n" +
                "\n" +
                "Items that are safe to handle and ingest for humans, including certain foods and medications we may take on a daily basis, can cause huge problems for dogs. Furthermore, pets who suffer accidental poisoning can experience gastrointestinal and neurological issues, cardiac and respiratory distress, or even coma and death."
    )
    val thirdBanner = DogeData(
        "https://www.dogtime.com/assets/uploads/2017/09/liver-cancer-dogs-1.jpg",
        "Liver Cancer In Dogs: Symptoms, Causes, & Treatments",
        "Liver cancer in dogs is a tumorous growth in the lining of the liver, which is the organ responsible for removing toxins for the body, aiding in digestion, and helping with blood clotting.\n" +
                "\n" +
                "Tumors in dogs’ livers are usually benign, and cancerous tumors of the liver most often result from metastatic cancers that originated elsewhere in the body and spread to the liver."
    )
    val fourthBanner = DogeData(
        "https://www.dogtime.com/assets/uploads/2016/03/dog-tax-season-e1551903666169.jpg",
        "Pet Poison Prevention Awareness Month: Top 10 Poison Prevention Tips For Dogs",
        "March is National Pet Poison Prevention Awareness Month, and the third full week of March is National Poison Prevention Week. In 2021, it starts on March 21st.\n" +
                "\n" +
                "It’s an important time to learn about some crucial ways to ensure our dogs have safe and happy lives. So let’s talk about ways to keep them safe from accidental poisoning."
    )
    return arrayListOf(firstBanner, secondBanenr, thirdBanner, fourthBanner)
}