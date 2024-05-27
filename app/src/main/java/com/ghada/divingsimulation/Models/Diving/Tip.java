package com.ghada.divingsimulation.Models.Diving;

import java.util.ArrayList;

public class Tip {
    String tipName;
    String tipDesc;
    String tipImage;
    String tipSubImage;


    public Tip() {
    }

    public Tip(String tipName, String tipDesc, String tipImage, String tipSubImage) {
        this.tipName = tipName;
        this.tipDesc = tipDesc;
        this.tipImage = tipImage;
        this.tipSubImage = tipSubImage;
    }

    public static ArrayList<Tip> createTips() {
        ArrayList<Tip> tips = new ArrayList<>();

        String[][] tipData = {
                {"Get Certified", "Sure, you can take the 1-day PADI Discover Scuba Diving course if you just want to test the waters, but trust us, you will fall in love and you will want to dive more and more! Now, that’s when you want to get the full PADI Open Water certification–it’s truly worth it.", "", ""},
                {"Choose a reputable dive center", "Before you book your dive course or dive trip, make sure the dive center is reputable and has experienced staff. We normally suggest going with a dive center that is local to the sites you are exploring. Our dive instructors and dive masters at Mimpi have been diving the sites near the resorts for more than 20 years. They know every nook and cranny, and this doesn’t just make it more fun (they know where to spot all the cool marine life!) but also safer. Lastly, never let price be the main decision maker. Your comfort and safety is paramount here.", "", ""},
                {"Get fit", "No, you don’t need to be an aspiring athlete or have a high-definition six-pack. But scuba diving is a sport, and like any other sport, it can be physically demanding, and so it’s important to be in good physical shape. Make sure you’re comfortable swimming and can handle carrying heavy gear.", "", ""},
                {"Learn to breathe", "Breathing underwater is different from breathing on land. Practice taking slow, deep breaths and exhaling fully. Never, ever hold your breath underwater.", "", ""},
                {"Keep calm", "It’s natural to feel nervous when diving, especially as a beginner. Remember to stay calm and take deep breaths to help you relax. Another perk of staying calm is that your oxygen tank will also last longer!", "", ""},
                {"Clear your ears", "As you descend, the pressure on your ears will increase. Learn how to clear your ears by pinching your nose and blowing gently. Do it as frequently as you need throughout your dive.", "", ""},
                {"Clear your mask", "This is one of the first skills your instructor will teach you during an Open Water course. Sometimes water can seep into your mask during your dive or your mask lens get foggy. These are two completely normal situations that can seriously take the fun out of your dive. Learn how to clear your mask by exhaling through your nose and practice it often.", "", ""},
                {"Use a dive computer", "When beginner divers ask us what equipment they should purchase as their first investment, our answer is almost always: A dive computer. It is an essential tool for scuba diving that will help you track your depth, time, and decompression stops. You’d be hard pressed to find an experienced diver who doesn’t wear one every time they go diving. Seriously.", "", ""},
                {"Check your gear", "Before every dive, make sure your gear is in good working order. Check for any leaks, tears, or other issues. Check if the gas supply is working just fine. There’s an acronym that can help you here: BWRAF, and for easier memorization you can use the fun mnemonic device of Breakfast-With-Rice-And-Fish. B = BCD check, W = Weights check, R = Releases check, A = Air check, F = Final overall check!", "", ""},
                {"Signal your buddy", "Always dive with a buddy and agree on a set of hand signals before you begin. Practice signaling to each other on land. It’s pretty much the only language you have underwater.", "", ""},
                {"Be aware of your surroundings", "Keep an eye on your surroundings and avoid touching or disturbing any marine life. When underwater, we are their guests and so let’s stay respectful of our hosts here.", "", ""},
                {"Don’t hold your breath", "Holding your breath can be dangerous while scuba diving. Make sure you’re breathing continuously throughout your dive.", "", ""},
                {"Stay hydrated", "Dehydration can be a problem while diving, so make sure you’re drinking plenty of water before and after your dive.", "", ""},
                {"Know your limits", "Don’t push yourself too hard while diving. Know your limits and stick to them. It’s always better to be safe than sorry.", "", ""},
                {"Practice buoyancy control", "Buoyancy control is essential for diving. Practice maintaining a neutral buoyancy so you don’t damage any marine life or disturb the environment. Mastering your buoyancy will also allow you to enjoy your dive to the fullest because you won’t have to spend the whole time adjusting your buoyancy and missing out on the beautiful scenery!", "", ""},
                {"Know the signs of nitrogen narcosis and decompression sickness", "Nitrogen narcosis can occur at deeper depths and can cause disorientation and confusion. Know the signs and ascend if you start experiencing the ‘drunk’ feeling that is characteristic of nitrogen narcosis. Decompression sickness (DCS) is a risk factor for divers who are doing deep or long dives, cold water, heavy exercise at depth, or rapid ascents. Always contact DAN or a physician trained in dive medicine in cases of suspected decompression illness — even if the signs and symptoms appear resolved.", "", ""},
                {"Follow the dive plan", "Always follow the dive plan and stay within the limits of your certification.", "", ""},
                {"Check your air", "Make sure to check your air supply regularly and communicate with your buddy about your air consumption.", "", ""},
                {"Stay warm", "Diving can be cold, so make sure you’re wearing the appropriate wetsuit or drysuit for the water temperature.", "", ""}
        };

        for (String[] data : tipData) {
            String tipName = data[0];
            String tipDesc = data[1];
            // TipImage and TipSubImage are empty for all tips
            tips.add(new Tip(tipName, tipDesc, "", ""));
        }

        return tips;
    }

    public String getTipName() {
        return tipName;
    }

    public void setTipName(String tipName) {
        this.tipName = tipName;
    }

    public String getTipDesc() {
        return tipDesc;
    }

    public void setTipDesc(String tipDesc) {
        this.tipDesc = tipDesc;
    }

    public String getTipImage() {
        return tipImage;
    }

    public void setTipImage(String tipImage) {
        this.tipImage = tipImage;
    }

    public String getTipSubImage() {
        return tipSubImage;
    }

    public void setTipSubImage(String tipSubImage) {
        this.tipSubImage = tipSubImage;
    }

    @Override
    public String toString() {
        return "Tip{" +
                "tipName='" + tipName + '\'' +
                ", tipDesc='" + tipDesc + '\'' +
                ", tipImage='" + tipImage + '\'' +
                ", tipSubImage='" + tipSubImage + '\'' +
                '}';
    }


}
