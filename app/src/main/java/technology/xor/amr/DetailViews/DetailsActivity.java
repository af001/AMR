package technology.xor.amr.DetailViews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import technology.xor.amr.R;

public class DetailsActivity extends AppCompatActivity {

    private List<Details> details;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reference_main);

        Intent intent = getIntent();
        String easyPuzzle = intent.getExtras().getString("site");

        rv = (RecyclerView) findViewById(R.id.detail_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        GetSiteName(easyPuzzle);
    }

    private void GetSiteName(String name) {
        switch (name) {
            case "ALABASTER":
                InitializeAlabaster();
                InitializeAdapter();
                break;
            case "AMBER":
                InitializeAmber();
                InitializeAdapter();
                break;
            case "AMETHYST":
                InitializeAmethyst();
                InitializeAdapter();
                break;
            case "AMETHYST DETOUR":
                InitializeAmethystDetour();
                InitializeAdapter();
                break;
            case "AQUAMARINE":
                InitializeAquamarine();
                InitializeAdapter();
                break;
            case "BERYL":
                InitializeBeryl();
                InitializeAdapter();
                break;
            case "BLOODSTONE":
                InitializeBloodstone();
                InitializeAdapter();
                break;
            case "CITRINE DETOUR":
                InitializeCitrineDetour();
                InitializeAdapter();
                break;
            case "CORAL":
                InitializeCoral();
                InitializeAdapter();
                break;
            case "DIAMOND":
                InitializeDiamond();
                InitializeAdapter();
                break;
            case "EMERALD":
                InitializeEmerald();
                InitializeAdapter();
                break;
            case "GRANITE":
                InitializeGranite();
                InitializeAdapter();
                break;
            case "GARNET":
                InitializeGarnet();
                InitializeAdapter();
                break;
            case "JADE":
                InitializeJade();
                InitializeAdapter();
                break;
            case "JASPER":
                InitializeJasper();
                InitializeAdapter();
                break;
            case "JADE DETOUR":
                InitializeJadeDetour();
                InitializeAdapter();
                break;
            case "ONYX":
                InitializeOnyx();
                InitializeAdapter();
                break;
            case "OBSIDIAN":
                InitializeObsidian();
                InitializeAdapter();
                break;
            case "OPAL":
                InitializeOpal();
                InitializeAdapter();
                break;
            case "PERIDOT":
                InitializePeridot();
                InitializeAdapter();
                break;
            case "QUARTZ":
                InitializeQuartz();
                InitializeAdapter();
                break;
            case "SAPPHIRE":
                InitializeSapphire();
                InitializeAdapter();
                break;
            case "SUNSTONE":
                InitializeSunstone();
                InitializeAdapter();
                break;
            case "ZIRCON":
                InitializeZircon();
                InitializeAdapter();
                break;
        }
    }

    private void InitializeAlabaster() {
        details = new ArrayList<>();
        details.add(new Details("Question: Using the directory, what's the grave number for Colonel Robert L. Howard, US MoH Recipient?", "Answer: Grave 138"));
        details.add(new Details("Task: Answer the question on H4V8.\nQuestion: How many Medals of Honor have been awarded to unknown Soldiers?", "Answer: 4 – Located in Museum at the Memorial Amphitheater – Behind the Tomb of the Unknowns"));
        details.add(new Details("Question: When facing the Eternal Flame, what date is given for the person buried on the far right?", "Answer: August 23, 1956. Headstone states “Daughter” and the date."));
        details.add(new Details("Task: Take a group photo within the Amphitheater at the Tomb of the Unknowns.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeAmber() {
        details = new ArrayList<>();
        details.add(new Details("Question: What Roman numerals represent the state of Maryland?", "Answer: MDCCLXXXVIII. Etched in stone at the top of the Lincoln Memorial- outside – on the Reflection Pool side."));
        details.add(new Details("Task: Answer the question on F3L7.\nQuestion: What year did Ghana issue a stamp honoring Abraham Lincoln?", "Answer: 1959 – Stamp is on display in the lower level of the museum. The stamp commemorates the 150th birthday anniversary of Lincoln. Lincoln was born in 1809."));
        details.add(new Details("Question: What speech is inscribed on the wall on statue’s right side?", "Answer: Gettysburg Address"));
        details.add(new Details("Task: Take a group photo within the statue.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeAmethyst() {
        details = new ArrayList<>();
        details.add(new Details("Question: Who was the Design Architect of this Memorial?", "Answer: Friedrich St. Florian"));
        details.add(new Details("Task: Answer the question on J7W0.\nQuestion: Standing on the seal in the Atlantic Pavilion, what state column is at a 215° azimuth?", "Answer: Delaware"));
        details.add(new Details("Question: How many gold stars are on the Freedom Wall?", "Answer: 4,048 - Located on display in front of the stars – The 4,048 gold stars commemorate the more than 400,000 Americans who gave their lives in the war."));
        details.add(new Details("Task: Take a group photo in front of the Pacific Pavilion", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeAmethystDetour() {
        details = new ArrayList<>();
        details.add(new Details("Question: Who was the Design Architect of the Memorial?", "Answer: Friedrich St. Florian"));
        details.add(new Details("Task: Take a group photo with the Atlantic Pavilion.", "Answer: None"));
        details.add(new Details("Task: Continue on with your original plan.", "Answer: None"));
    }

    private void InitializeAquamarine() {
        details = new ArrayList<>();
        details.add(new Details("Question: What year was “The Four Freedoms” speech delivered?", "Answer: 1941"));
        details.add(new Details("Task: Answer the question on I3Y9.\nQuestion: Who designed FDR’s wheelchair?", "Answer: He did"));
        details.add(new Details("Question: What quote is to the right of the statue of a citizen listening to the Fireside Chat?", "Answer: I never forget that I live in a house owned by all the American people and that I have been given their trust."));
        details.add(new Details("Task: Take a group photo with the Four Freedoms", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeBeryl() {
        details = new ArrayList<>();
        details.add(new Details("Question: What book did Rousseau write?", "Answer: Du Contract Social"));
        details.add(new Details("Task: Answer the question on N6X0.\nQuestion: What bridge is approximately 315 degrees from the George Mason memorial?", "Answer: Inlet Bridge"));
        details.add(new Details("Question: Who regarded George Mason as one of the wisest men of his generation?", "Answer: Thomas Jefferson - Answer found on information placard as you enter the memorial"));
        details.add(new Details("Task: Take a group photo in front of the Pacific Pavilion", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeBloodstone() {
        details = new ArrayList<>();
        details.add(new Details("Question: What is the uppermost inscription inside this memorial?", "Answer: I have sworn upon the alter of God eternal hostility against every form of tyranny over the mind of man."));
        details.add(new Details("Task: Answer the question on A8N1.\nQuestion: In the painting of the Declaration of Independence, who is standing to Thomas Jefferson’s left?", "Answer: Benjamin Franklin - Painting is located in the Museum on the lower level."));
        details.add(new Details("Question: Who sculptured the Thomas Jefferson statue?", "Answer: R. Evans - Located on the rear, left side of statue."));
        details.add(new Details("Task: Take a group photo with the statue.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeCitrineDetour() {
        details.add(new Details("Question: What members signed from the state that is famous for the “Boston Tea Party?", "Answer: Robert Treat Paine, John Hancock, Samuel Adams, John Adams, and Elbridge Gerry"));
        details.add(new Details("Task: Take a group photo in front of Massachusetts.", "Answer: None"));
        details.add(new Details("Task: Continue with your original plan.", "Answer: None"));
    }

    private void InitializeCoral() {
        details = new ArrayList<>();
        details.add(new Details("Question: Over which highway did the Second Transcontinental Convoy start?", "Answer: Over the Bankhead Highway, June 14, 1920 - Found on the monument."));
        details.add(new Details("Task: Answer the question in E2V0.\nQuestion: When standing at this site facing north, what iconic structure is immediately to your front?", "Answer:  The White House - “Zero Milestone” is inscribed on the monument facing North."));
        details.add(new Details("Question: What major memorial is due South?", "Answer: Thomas Jefferson Memorial - Candidates will have to look beyond the Washington Monument to find the answer."));
        details.add(new Details("Task: Take a group photo with the iconic structure immediately north of CORAL.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeDiamond() {
        details = new ArrayList<>();
        details.add(new Details("Question: What is the name of the sculpture that includes a caisson at this location?", "Answer: Battery of Horse Artillery"));
        details.add(new Details("Task: Answer the question on T8B3.\nQuestion: Who was the sculptor of this memorial?", "Answer: Henry Merwin Shrady - Located on the rear of the memorial (Last name is on every statue – full name is on placard below caisson)."));
        details.add(new Details("Question: How many bronze sculptures are at this memorial?", "Answer: 9 – Grant, Caisson, Cavalry, 4 lions, 1 sculpture on each side of Grant"));
        details.add(new Details("Task: Take a group photo with Grant and his horse.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeEmerald() {
        details = new ArrayList<>();
        details.add(new Details("Question: Where can the name “Larry W. Butler” be found?", "Answer: 18E, Line 91 - Found in the Fallen Registry."));
        details.add(new Details("Task: Answer the question on L6B9.\nQuestion: What is the height of each individual letter on the wall?", "Answer: 0.53 inch (Pamphlet) or ½ inch (measured from side of compass)."));
        details.add(new Details("Question: What names are to the immediate left and immediate right of his?", "Answer: Richard T. Brown and Dana C. Darnell"));
        details.add(new Details("Task: Take a group photo of the Three Servicemen statue.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeGarnet() {
        details = new ArrayList<>();
        details.add(new Details("Question: What is written under the unit patch of the UAV(S) that flew 196 combat missions in Afghanistan?", "Answer: Big Safari Program - Hanging in front of plaque on the 2nd floor Air side of museum."));
        details.add(new Details("Task: Answer the question on B7F6.\nQuestion: Name 3 astronauts that died on the first Apollo mission?", "Answer: Virgil I. Grissom, Edward H. White II, Roger B. Chaffee - On a plaques, Space side, 2nd Floor."));
        details.add(new Details("Question: What prestigious award did WWII flying ace Maj. Richard Bong receive?", "Answer: Medal of Honor - Hanging around his neck in the picture- 2nd floor Air side."));
        details.add(new Details("Task: Take a group photo in front of the Project Mercury display.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeGranite() {
        details = new ArrayList<>();
        details.add(new Details("Question: What quote was made by Commodore Oliver Perry on September 10, 1813?", "Answer: We have met the enemy and they are ours - Found behind the sailor."));
        details.add(new Details("Task: Answer the question on Z0M3.\nQuestion: What is the motto of the U.S. Navy Explosive Ordnance Disposal Team?", "Answer: Initial success or total failure - Found to the left side of the Lone Sailor, on his right."));
        details.add(new Details("Question: Who was the sculptor of the Naval Special Warfare Placard?", "Answer: Leo C. Irrera - Found on the street side of the memorial."));
        details.add(new Details("Task: Take a group photo with The Lone Sailor.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeJade() {
        details = new ArrayList<>();
        details.add(new Details("Question: How big (carats) is the Hazen Diamond necklace?", "Answer: 131.4 Carats - Inside the Geology, Gems and Minerals exhibit. Part of the National Gem Collection."));
        details.add(new Details("Question: How far do Wildebeest migrate each year in search of food?", "Answer: 1,450km or 900 miles - Inside the Mammal exhibit"));
        details.add(new Details("Task: Take a group photo with the Elephant in the main lobby.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeJadeDetour() {
        details = new ArrayList<>();
        details.add(new Details("Question: What is the measurement in CARATS of the DOM PEDRO AQUAMARINE?", "Answer: 10,363"));
        details.add(new Details("Task: Take a group photo with the stone.", "Answer: None"));
        details.add(new Details("Task: Continue on with your original plan.", "Answer: None"));
    }

    private void InitializeJasper() {
        details = new ArrayList<>();
        details.add(new Details("Question: John Bradley held what profession at the time of the flag raising?", "Answer: Pharmacist Mate 2nd Class - If plaque is missing, accept Marine."));
        details.add(new Details("Task: Answer the question on S4W2.\nQuestion: How many M1 rifles are seen at this memorial?", "Answer: 4 – Count them – 3x M-1 Garand’s, and 1x M-1 Carbine"));
        details.add(new Details("Question: Who was the sculptor?", "Answer: Felix W. DeWeldon - Located on one of the info plaques around the memorial."));
        details.add(new Details("Task: Take a group photo with JASPER.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeOnyx() {
        details = new ArrayList<>();
        details.add(new Details("Question: What is the last sentence from the speech given by President Reagan on October 5th, 1988?", "Answer: And only then can we be sure that it will never arise again - Located outside of the museum on the wall near the entrance."));
        details.add(new Details("Task: Answer the question on U2X8.\nQuestion: What position does William J. Lowenberg hold?", "Answer: Vice Chairman - Info on plaque on back entrance of Museum (14th Street)"));
        details.add(new Details("Question: When did General Dwight D. Eisenhower visit Ohrdruf Concentration Camp?", "Answer: April 15, 1945 - Located outside of the museum on the wall near the entrance."));
        details.add(new Details("Task: Take a group photo with ONYX.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeObsidian() {
        details = new ArrayList<>();
        details.add(new Details("Question: Identify the reference number of John McCain, a 2008 Presidential Candidate using the Prisoner of War database.", "Answer: 242 - 2nd floor POW database"));
        details.add(new Details("Task: Answer the question on K7U1.\nQuestion: Use the Interactive display in the Public Vaults and answer the following question: How many of the Lincoln assassination conspirators were sentenced to hang?", "Answer: Four - Focus in on assassinations."));
        details.add(new Details("Question: Who does #11 depict in the Declaration of Independence Mural in the Rotunda?", "Answer: John Hancock - Use shadow key below mural to identify #11."));
        details.add(new Details("Task: Take a group photo within the Rotunda.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeOpal() {
        details = new ArrayList<>();
        details.add(new Details("Question: In the regional gardens, what is the common name for CTENIUM AROMATICUM?", "Answer: TOOTHACHE GRASS - Found in Regional Garden Pamphlet"));
        details.add(new Details("Question: What year did Congress designate the rose as the National Flower?", "Answer: 1986 - Found on a pamphlet inside one of the mailboxes that are located in the Rose Garden."));
        details.add(new Details("Task: Take a group photo standing in the amphitheater in the garden.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializePeridot() {
        details = new ArrayList<>();
        details.add(new Details("Question: What is the capacity of the mail car at 85 miles per hour?", "Answer: 30,500lbs - Inside the train."));
        details.add(new Details("Task: Answer the question on G2T8.\nQuestion: What year did the post Office Department turn the airmail service over to private contractors?", "Answer: 1927"));
        details.add(new Details("Question:How many post offices were there 1890?", "Answer: 62,400 - Inside the blue truck."));
        details.add(new Details("Task: Take a group photo in front of the V-mail Display.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeQuartz() {
        details = new ArrayList<>();
        details.add(new Details("Question: When was the Vietnam Memorial erected at this site?", "Answer: 1977 – On the Eastern Wall"));
        details.add(new Details("Task: Answer the question on S7F7.\nQuestion: Which regiments of the 1st Division are honored here for suffering losses during World War II?", "Answer: 16th, 18th and 26th Regiments"));
        details.add(new Details("Question: What brigades had losses during Desert Storm from the 1st Division?", "Answer: 1st, 3rd, and 4th Brigades - Located on the Eastern ground of the monument."));
        details.add(new Details("Task: Take a group photo with the center statue.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeSapphire() {
        details = new ArrayList<>();
        details.add(new Details("Question: How many U.S. service members were killed in the war?", "Answer: 54,246 - Located on the curb at the pond."));
        details.add(new Details("Task: Answer the question on L8M0.\nQuestion: When was the memorial dedicated?", "Answer: July 27, 1995 - On pamphlet and engraved on stone at the memorial."));
        details.add(new Details("Question: How many U.N. countries participated in the war?", "Answer: 22 - On pamphlet and countries are engraved in stone boarding the sidewalk next to the memorial."));
        details.add(new Details("Task: Take a group photo with the Soldiers.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeSunstone() {
        details = new ArrayList<>();
        details.add(new Details("Question: What was the Moongate Garden inspired by?", "Answer: Temple of Heaven - The Moongate Garden was inspired by architectural and symbolic elements found in the Temple of Heaven, a masterpiece of Chinese architecture and landscape design built in Beijing during the Ming Dynasty (1368-1644)."));
        details.add(new Details("Question: How many acres is SUNSTONE?", "Answer: 4.2 acres - Located on the placard toward the front of the entrance."));
        details.add(new Details("Task: Take a group photo with the Moongate.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeZircon() {
        details = new ArrayList<>();
        details.add(new Details("Question: What is the name of the individual with the earliest date of death from the District of Columbia?", "Answer: Francis M. Doyle (29 December 1871)"));
        details.add(new Details("Task: Answer the question on M7M9.\nQuestion: On which panel can the name Gary L Williams be found?", "Answer: Panel 60W - Can be found at any of four directories surrounding the monument."));
        details.add(new Details("Question: Who is credited for the quote 'In Valor There Is Hope'?", "Answer: Tacitus - Underneath the lion statue."));
        details.add(new Details("Task: Take a group photo with the ZIRCON Crest.", "Answer: None"));
        details.add(new Details("Task: Continue to the next site.", "Answer: None"));
    }

    private void InitializeAdapter(){
        DetailsAdapter adapter = new DetailsAdapter(details);
        rv.setAdapter(adapter);
    }
}
