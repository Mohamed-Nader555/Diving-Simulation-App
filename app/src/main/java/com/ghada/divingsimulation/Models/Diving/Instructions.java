package com.ghada.divingsimulation.Models.Diving;

import java.io.Serializable;
import java.util.ArrayList;

public class Instructions implements Serializable {
    String instructionTitle;
    String instructionDetails;

    public Instructions() {
    }

    public Instructions(String instructionTitle, String instructionDetails) {
        this.instructionTitle = instructionTitle;
        this.instructionDetails = instructionDetails;
    }

    public static ArrayList<Instructions> createInstructions() {
        ArrayList<Instructions> instructions = new ArrayList<>();

        String[][] instructionData = {
                {"1. Stop", "Stop everything going on and try to think clearly."},
                {"2. Assess", "Assess the scene of the incident for hazards. \nIf the scene appears unsafe, do not proceed with rescue efforts. Call 9-1-1. \nIf the scene can be made safe while ensuring the safety of all rescuers, proceed with rescuing the injured diver."},
                {"3. Use", "Use any pertinent resources available to you. \nIf you need others to help, direct them with clear and concise instructions. People will follow a leader’s instruction in stressful times. In Hawaii, there can be many experienced watermen near the shoreline at any given time. Ask for help if you need."},
                {"4. Exposure protection", "Use nitrile gloves and a face mask if bodily fluids are present. These items are in the emergency dive first aid kit."},
                {"5. Check", "Check the victim for level of responsiveness using the acronym AVPU.\na. A – Injured diver is aware and oriented to him/herself, the time, and the location.\nb. V – Injured diver gives a response when presented with a verbal stimulus.\nc. P – Injured diver gives a response only when presented with a painful stimulus.\nd. U – Injured diver is completely unresponsive and shows no mental function."},
                {"6. Call for help - 9-1-1.", "If the injured diver is not A on AVPU scale or if there is any question whether EMS may be needed, call 9-1-1.\na. Reference the Scientific Diving Emergency Contacts page at the end of this plan and the copy of your dive plan for numbers and addresses of care facilities near the dive site.\nb. If appropriate, call the Diver’s Alert Network for consultation and advice.\nc. Call the DSO or other DCB members for help and further guidance."},
                {"7. Care for the victim.", "Always prioritize ABC’s – airway, breathing, and circulation. If the victim is unconscious, start your care with Look-Listen-Feel for both breathing and the presence of a pulse. If a pulse is present without breathing, rescue breaths are appropriate. If neither pulse nor breathing are present, initiate CPR.\na. Position the injured diver on his/her back.\nb. Give 30 compressions about 2” deep on the center of the sternum at a rate of 100-120 compressions per minute.\nc. Open victim's airway with head-tilt, chin-lift method.\nd. Provide two breaths to the victim.\ne. Continue this pattern of 30 compressions to 2 breaths for 2 minutes (about 5 sets of 30:2).\nf. Look, listen, and feel for signs of life every 2 minutes.\ng. Continue this process as long as possible."},
                {"8. Oxygen.", "Generally, the best thing for the majority of diving accidents is administration of 100% Oxygen. The first choice of oxygen delivery equipment for any injured diver is the demand regulator included in the oxygen kit. Second choice is the non-resuscitator bag with the oxygen hook up."},
                {"9. Treat for shock.", "a. Maintaining the diver’s temperature.\nb. If possible, place the injured diver supine with legs elevated 10-12” (if head, neck, or back injury is not suspected). If this is not possible, place diver in a position of comfort.\nc. Provide oxygen.\nd. Do not administer fluids orally."},
                {"10. Notes.", "If there are enough people present, assign someone to take notes on the patient’s state. Note times of readings and/or changes in symptoms. Write clearly. This information should be handed off to paramedics when they arrive on the scene and should stay with the patient."},
                {"11. Accident reports.", "Each diver at the scene should fill out an accident report while details are fresh in their minds. As time passes, many details often slip. Please be honest and accurate as to what you saw you. Each diver is to submit their own story and to not corroborate stories with others. Accident reports will be submitted to the HPU Dive control board within 24 hours of the incident."},
                {"12. Dive gear.", "Do not disassemble the dive gear of the injured diver in the case of a serious accident. Please keep in-tact and label with the diver’s name. The dive gear will be passed on to the DSO and the DCB."}
        };

        for (String[] data : instructionData) {
            String instructionTitle = data[0];
            String instructionDetails = data[1];
            instructions.add(new Instructions(instructionTitle, instructionDetails));
        }

        return instructions;
    }

    public String getInstructionTitle() {
        return instructionTitle;
    }

    public void setInstructionTitle(String instructionTitle) {
        this.instructionTitle = instructionTitle;
    }

    public String getInstructionDetails() {
        return instructionDetails;
    }

    public void setInstructionDetails(String instructionDetails) {
        this.instructionDetails = instructionDetails;
    }

    @Override
    public String toString() {
        return "Instructions{" +
                "instructionTitle='" + instructionTitle + '\'' +
                ", instructionDetails='" + instructionDetails + '\'' +
                '}';
    }
}
