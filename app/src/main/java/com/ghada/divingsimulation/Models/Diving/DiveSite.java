package com.ghada.divingsimulation.Models.Diving;

import java.io.Serializable;
import java.util.ArrayList;

public class DiveSite implements Serializable {
    private String name;
    private String location;
    private String maxDepth;
    private String entryType;
    private String bottomComposition;
    private String aquaticLife;
    private String rating;
    private String img;

    // Constructor
    public DiveSite(String name, String location, String maxDepth, String entryType, String bottomComposition, String aquaticLife, String rating, String img) {
        this.name = name;
        this.location = location;
        this.maxDepth = maxDepth;
        this.entryType = entryType;
        this.bottomComposition = bottomComposition;
        this.aquaticLife = aquaticLife;
        this.rating = rating;
        this.img = img;
    }

    public static ArrayList<DiveSite> createDiveSites() {
        ArrayList<DiveSite> diveSites = new ArrayList<>();

        String[][] diveSiteData = {
                {"Abu Dabbab", "Marsa Alam, Egypt", "51-60ft/16-18m", "Shore", "Sand", "Big Animals", "4.17"},
                {"Abu Hashish", "Hurghada, Red Sea/hurghada, Egypt", "91-100ft/28-30m", "Boat", "Sand", "Plenty To See", "4.00"},
                {"Abu Nuhas Reef", "Egypt", "N/A", "N/A", "N/A", "N/A", "5.00"},
                {"Abu Ramada", "Hurghada, Egypt", "41-50ft/12-15m", "Boat", "Sand", "Plenty To See", "4.29"},
                {"Aida", "Egypt", "Over 150ft/46m", "Boat", "No Bottom", "Plenty To See", "4.33"},
                {"Al Quseir Housereef", "Al Quseir, Egypt", "N/A", "N/A", "N/A", "N/A", "4.00"},
                {"Blue Hole", "Dahab, Sinai, Egypt", "Over 150ft/46m", "Shore", "Sand", "Plenty To See", "4.30"},
                {"Brother Islands", "Egypt", "Over 150ft/46m", "Boat", "N/A", "Big Animals", "4.38"},
                {"Chrisoula K", "Egypt", "N/A", "Boat", "N/A", "N/A", "5.00"},
                {"Coraya Bay", "Marsa Alam, Egypt", "Over 150ft/46m", "Shore", "Rock", "Plenty To See", "4.00"},
                {"Daedalus Reef - Abu Kizan", "Egypt", "Over 150ft/46m", "Boat", "No Bottom", "Plenty To See", "4.75"},
                {"Dahab", "Egypt", "Over 150ft/46m", "Shore", "Sand", "Big Animals", "N/A"},
                {"Dahab - The Canyon", "Dahab, Egypt", "Over 150ft/46m", "Shore", "N/A", "Might See Something", "5.00"},
                {"Dahab Islands", "Egypt", "31-35ft/9-11m", "Shore", "Sand", "Might See Something", "4.75"},
                {"eel garden", "Egypt", "51-60ft/16-18m", "Shore", "Sand", "Plenty To See", "4.50"},
                {"Elphinstone aka Elfinstone", "Marsa Alam, Southern Egypt, Egypt", "Over 150ft/46m", "Boat", "No Bottom", "Plenty To See", "4.08"},
                {"Giannis D", "Egypt", "N/A", "Boat", "N/A", "N/A", "5.00"},
                {"Gordon Reef", "Sinai, Egypt", "Over 150ft/46m", "Boat", "Sand", "N/A", "4.00"},
                {"Gota Ben El Gabal", "Hurghada, Red Sea, Egypt", "41-50ft/12-15m", "Boat", "Sand", "Plenty To See", "5.00"},
                {"Haggadah - RedSea", "Egypt", "91-100ft/28-30m", "Boat", "Sand", "Plenty To See", "5.00"},
                {"HMS Thistlegorm", "27°42’00\"N; 34°05’00\"E, Egypt", "101-110ft/31-34m", "Boat", "Sand", "Plenty To See", "4.33"},
                {"Jackson Reef - Straits of Tiran", "Red Sea, Egypt", "121-130ft/37-40m", "Boat", "Coral", "Plenty To See", "N/A"},
                {"Kimon M", "Egypt", "N/A", "N/A", "N/A", "N/A", "5.00"},
                {"light house", "Egypt", "111-120ft/34-37m", "Shore", "Sand", "Plenty To See", "4.50"},
                {"Lighthouse", "Dahab, Sinai, Egypt", "Over 150ft/46m", "Shore", "Sand", "Plenty To See", "4.00"},
                {"Mangrove Bay", "El Quesier, Egypt", "N/A", "Shore", "N/A", "Plenty To See", "4.00"},
                {"Marsa El Shagra", "Marsa Allam, Egypt", "121-130ft/37-40m", "Shore", "Sand", "Plenty To See", "5.00"},
                {"Near Garden", "Sinai, Egypt", "91-100ft/28-30m", "Boat", "Sand", "Plenty To See", "5.00"},
                {"Nemo City", "Egypt", "141-150ft/43-46m", "Boat", "Sand", "Plenty To See", "3.00"},
                {"North Wrecks Red Sea Egypt", "Hurghada-Egypt, Hurghada, Egypt", "71-80ft/22-24m", "Boat", "Sand", "Big Animals", "5.00"},
                {"Numedia aka railway wreck", "Egypt", "Over 150ft/46m", "Boat", "N/A", "Might See Something", "N/A"},
                {"P&O Carnatic", "Egypt", "N/A", "Boat", "N/A", "N/A", "5.00"},
                {"Panorama", "Safaga, Egypt", "Over 150ft/46m", "Boat", "Coral", "N/A", "5.00"},
                {"Rad Mohamed aka Ras Mohammed", "Sinai, Egypt", "Over 150ft/46m", "N/A", "N/A", "Plenty To See", "4.30"},
                {"Ras Disha", "Hurghada, Red Sea/hurghada, Egypt", "91-100ft/28-30m", "Boat", "Sand", "Might See Something", "3.50"},
                {"Ras Ghamila", "Egypt", "Over 150ft/46m", "Boat", "Sand", "Plenty To See", "5.00"},
                {"Redsea", "Egypt", "91-100ft/28-30m", "Boat", "Sand", "Plenty To See", "4.50"},
                {"Rosalie Möller", "Hurghada, Egypt", "81-90ft/25-27m", "Boat", "N/A", "Plenty To See", "N/A"},
                {"S.S. Numidia", "Egypt", "Over 150ft/46m", "Boat", "N/A", "Might See Something", "5.00"},
                {"Shab Sabrina", "Hurghada, Red Sea/hurghada, Egypt", "91-100ft/28-30m", "Boat", "Sand", "Plenty To See", "3.33"},
                {"Shark & yolanda Reef", "Sharm Al Sheikh, South Sinai Govern, Sharm Al Sheikh, South Sinai 46619, Egypt", "Over 150ft/46m", "Boat", "Coral", "Plenty To See", "N/A"},
                {"Shark and Yolanda Reef", "Sharm El-sheikh, Egypt", "Over 150ft/46m", "Boat", "No Bottom", "Might See Something", "4.50"},
                {"Shark bay", "Sharm El-Sheikh, South Sinai, Egypt", "Over 150ft/46m", "Shore", "Coral", "Plenty To See", "4.50"},
                {"Shark Observatory", "Sharm El Sheikh, Egypt", "N/A", "Boat", "N/A", "Plenty To See", "4.43"},
                {"Sharm El Sheikh", "Sharm El Sheikh, Egypt", "101-110ft/31-34m", "Boat", "N/A", "Big Animals", "4.62"},
                {"Small Giftun Island", "Hurghada, Red Sea, Egypt", "Over 150ft/46m", "Boat", "Coral", "Plenty To See", "5.00"},
                {"St. John", "Egypt", "91-100ft/28-30m", "Boat", "Sand", "Plenty To See", "4.00"},
                {"T43 minesweeper (Elmina)", "Egypt", "N/A", "N/A", "N/A", "N/A", "4.00"},
                {"Taba Hilton", "Taba, Egypt", "N/A", "Shore", "N/A", "Might See Something", "N/A"},
                {"The Barge", "Egypt", "N/A", "N/A", "N/A", "N/A", "5.00"},
                {"The Canyon", "Dahab, Egypt", "Over 150ft/46m", "Shore", "Sand", "Plenty To See", "4.36"},
                {"The Ulysses", "Egypt", "91-100ft/28-30m", "Boat", "Coral", "Plenty To See", "4.00"},
                {"Tiran Strays", "Sharm Al Sheikh, Egypt", "Over 150ft/46m", "Boat", "No Bottom", "Plenty To See", "4.45"},
                {"Torfa el Karuf", "Egypt", "61-70ft/19-21m", "Shore", "Sand", "Might See Something", "3.00"},
                {"Wadi Gonay", "Dahab, South Sinai, Egypt", "81-90ft/25-27m", "Shore", "Sand", "Plenty To See", "4.50"}
        };

        for (int i = 0; i < diveSiteData.length; i++) {
            String[] data = diveSiteData[i];
            String name = data[0];
            String location = data[1];
            String maxDepth = data[2];
            String entryType = data[3];
            String bottomComposition = data[4];
            String aquaticLife = data[5];
            String rating = data[6];
            String img = "diving_site_" + (i + 1); // Modify img attribute based on the index
            diveSites.add(new DiveSite(name, location, maxDepth, entryType, bottomComposition, aquaticLife, rating, img));
        }

        return diveSites;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(String maxDepth) {
        this.maxDepth = maxDepth;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getBottomComposition() {
        return bottomComposition;
    }

    public void setBottomComposition(String bottomComposition) {
        this.bottomComposition = bottomComposition;
    }

    public String getAquaticLife() {
        return aquaticLife;
    }

    public void setAquaticLife(String aquaticLife) {
        this.aquaticLife = aquaticLife;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "DiveSite{" + "name='" + name + '\'' + ", location='" + location + '\'' + ", maxDepth='" + maxDepth + '\'' + ", entryType='" + entryType + '\'' + ", bottomComposition='" + bottomComposition + '\'' + ", aquaticLife='" + aquaticLife + '\'' + ", rating='" + rating + '\'' + ", img='" + img + '\'' + '}';
    }

}