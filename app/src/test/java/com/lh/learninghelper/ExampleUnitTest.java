package com.lh.learninghelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void run() throws JSONException {
        String question = "Read the following text and fill each of the numbered spaces with ONE suitable word. Write your answers on the ANSWER SHEET.\n" +
                "      In the years after World War II, Americans typically assumed the full responsibilities of adulthood by their late teens or early 20s. Most young men had (21 )__ school and were working full-\n" +
                "time, and most young women were (22)__ and raising children. People who grew (23) __in this era of growing affluence were economically serf-sufficient and able to take care of others by the time they had weathered adolescence. Today, adulthood no longer (24) __ when adolescence ends.\n" +
                "      Social scientists are beginning to recognize a new phase of life: early adulthood. Some features of this stage resemble coming of age (25) __ the late 19th and early 20th centuries,(26)__ youth fingered in a state of semi-autonomy, waiting (27)__ they were sufficiently well-off to marry, have children and establish an independent (28) __  However, there are important differences (29)__ how young people today define and achieve adulthood from those of both the recent and the more distant past.\n" +
                "      This new stage is not merely an extension of adolescence, (30) __ has been maintained in the mass media. Young adults are physically mature and often (31) __ impressive intellectual,\n" +
                "social and psychological skills. Nor are young people today reluctant to accept adult responsibilities. Instead, they are busy (32) __ up their educational credentials and practical skills in an ever more demanding labor market. Yet, many have not become fully adult, (33) __ they are not ready, or perhaps not permitted, to do (34) __ . For a growing number, this will not happen until their late 20s or even early 30s. In (35) __, American society will have to revise upward the “normal” age of full adulthood, and develop ways to assist young people through " +
                "the ever-lengthening transition.\n";
        JSONObject json = new JSONObject();

        json.put("aa", "bb");

        System.out.println(json);
    }
}