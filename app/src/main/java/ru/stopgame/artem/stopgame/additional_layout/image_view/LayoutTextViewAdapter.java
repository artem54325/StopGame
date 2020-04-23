package ru.stopgame.artem.stopgame.additional_layout.image_view;


import android.app.Activity;
import android.widget.LinearLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import ru.stopgame.artem.stopgame.R;
import ru.stopgame.artem.stopgame.additional_layout.views.SpoilerView;
import ru.stopgame.artem.stopgame.additional_layout.views.TagsView;
import ru.stopgame.artem.stopgame.additional_layout.views.YouTubeView;

public class LayoutTextViewAdapter {
    private LinearLayout layout;
    private LayoutHolder layoutHolder;
    private List<String> listHtml = new ArrayList<>();
    private List<String> listLayout = new ArrayList<>();
    private Activity activity;

    public LayoutTextViewAdapter(Activity activity, LinearLayout linearLayout) {//https://stopgame.ru/blogs/topic/82297 СПОЙЛЕР!!
        layout = linearLayout;
        this.activity=activity;
        layoutHolder = new LayoutHolder(activity);
    }

    public LayoutTextViewAdapter(Activity activity) {
        layout = new LinearLayout(activity);
        this.activity=activity;
        layoutHolder = new LayoutHolder(activity);
    }

    public LinearLayout setText(String html){
        if (html==null) return null;

        pars(html);

        for (int i=0;i<listLayout.size()||i<listHtml.size();i++){
            switch (listLayout.get(i)){
                case "review_image"://Картинка с текстом
                    layout.addView(layoutHolder.addImageView(listHtml.get(i)));
                    break;
                case "tags"://Тэги!
                    layout.addView(new TagsView(layout.getContext()).setTags(listHtml.get(i)));
                    break;
                case "spoiler-title"://Спойлеры
                    layout.addView(new SpoilerView(activity).setSpoiler(listHtml.get(i)));//listHtml.get(i)+"\"spoiler-body\">" + listHtml.get(i+1)
                    break;
                case "iframe_h"://Походу видео всё, но может быть только ютуб!
                    layout.addView(new YouTubeView(layout.getContext()).setViewYouTube(listHtml.get(i)));
                    break;
                case "fotorama"://НЕсколько картинок
//                    break;
                case "right":
                    listHtml.set(i,listHtml.get(i).replace("\"right\">",""));
                case "center":
                    listHtml.set(i,listHtml.get(i).replace("\"center\">",""));
                case "text":
                    layout.addView(layoutHolder.addTextImage(listHtml.get(i)));
                    break;
                case "best_topic_icon":
                    layout.addView(layoutHolder.addImageView(null, R.drawable.best_blog));//<img src="https://images.stopgame.ru/site/logos/best_blog.jpg">
                    break;
                default:
                    System.out.println(listHtml.get(i) + " default wwewwww");
//                    layout.addView(layoutHolder.addTextView(listHtml.get(i)));
            }
        }

        return layout;
    }

    public LinearLayout setObjectSave(){
        return null;
    }


    private void pars2(String html) {
        listHtml = new ArrayList<>();
        listLayout = new ArrayList<>();
        if (!html.contains("<div ")){//Это для комментариев, по идеи должно сработать
            listHtml.add(html);
            listLayout.add("text");
            return;
        }
        Document doc = Jsoup.parse("<body>"+html+"</body>");
        Elements elements = doc.select("body > div");
        StringBuffer bf = new StringBuffer();

        int numb = 0;
        //Версия 3
        for (int i=0;i<elements.size();i++){
            StringBuffer buffer = new StringBuffer();
            //Вариант 4
//            for (int a=0;a<elements.get(i).textNodes().size();a++){
//                buffer.append(elements.get(i).textNodes().get(a).text());
//                numb++;
//            }
            //Вариант 1
//            for (int a=elements.get(i).elementSiblingIndex();a<elements.get(i).siblingElements().size();a++) {
//                if (elements.get(i).siblingElements().get(a).html().contains("<div")) {
//                    break;
//                }
////                elements.get(i).getAllElements().
//                buffer.append(elements.get(i).siblingElements().get(a).html());
//            }
            //Вариант 2
            try {
                buffer.append(bf.toString().split(elements.get(i).html())[1].split(elements.get(i+1).html())[0]);
//                System.out.println(elements.get(i).html()+"\nwwe\n"+elements.get(i+1).html());
            }catch (ArrayIndexOutOfBoundsException e){
//                System.out.println(bf.toString().contains(elements.get(i).html()) + "\n\t"+(elements.get(i).html() + " wwwwww"));
                e.printStackTrace();
            }
            //Вариант 3

//                System.out.println(elements.get(i).textNodes().size() + " elements.get(i).textNodes().size()");
//                for (int a=elements.get(i).elementSiblingIndex();a<elements.get(i).siblingElements().size();a++) {
//                    if (elements.get(i).siblingElements().get(a).html().contains("<div")||elements.get(i).children().size()<a) {
//                        break;
//                    }
//                    try {
//                        buffer.append(elements.get(i).child(a).html());
//                    }catch (IndexOutOfBoundsException e){
//                        System.out.println(doc.html().contains(elements.get(i+1).html()) + "\n\t"+(elements.get(i).html() + " wwwwww"));
//                        e.printStackTrace();
//                    }
//                }
//                buffer.append(doc.html().split(elements.get(i).html())[1].split(elements.get(i+1).html())[0]);
//                System.out.println(elements.get(i).html()+"\nwwe\n"+elements.get(i+1).html());


            switch (elements.get(i).attr("class")){
                case "best_topic_icon":
                    listHtml.add(elements.get(i).html());
                    listLayout.add("best_topic_icon");
                    break;
                case "spoiler clear2":
//                    listHtml.add(elements.get(i).html());
//                    listLayout.add("spoiler-title");
                    break;
                case "iframe_h":
//                    listHtml.add(elements.get(i).html());
//                    listLayout.add("iframe_h");
                    break;
                case "iframe_wrapper":
                    listHtml.add(elements.get(i).html());
                    listLayout.add("iframe_h");
                    break;
//                case "":
//                    break;
//                case "":
//                    break;
//                case "":
//                    break;
                default:
            }
            if (!buffer.toString().equals("")) {
                listHtml.add(buffer.toString());
                listLayout.add("text");
            }
        }

        //Версия 1
//        Document doc = Jsoup.parse(html);
//        listHtml.add(doc.html().split(doc.select("div").get(0).html())[0]);
//        listLayout.add("text");
//        for (Element element:doc.select("div")){
//            switch (element.attr("class")){
//                case "spoiler-title spoiler-arrow-close":
//                    listLayout.add("spoiler-title");
//                    break;
//                case "spoiler-body":
//                    listLayout.add("spoiler-body");
//                    break;
//                case "iframe_h":
//                    listLayout.add("iframe_h");
//                    break;
//                case "fotorama":
//                    listHtml.add(element.html());
//                    break;
//                case "best_topic_icon":
//                    listLayout.add("best_topic_icon");
//                    break;
//                default:
//                    continue;
//            }
//            listHtml.add(element.html());
//            try {
//                if(!doc.html().split(element.html())[1].split("</div>")[1].split("<div")[0].trim().equals("")){
//                    listHtml.add(doc.html().split(element.html())[1].split("<div")[0]);
//                    listLayout.add("text");
//                }
//            }catch (Exception e){
////                e.printStackTrace();
//            }
//
//            System.out.println(element.html() +" html");
//        }

        //Версия 2
//        listHtml.add(list[0]);
//        listLayout.add("text");
//        for (int i = 1; i < list.length; i++) {
//            if (list[i].contains("\"best_topic_icon\">")) {
//                listHtml.add(list[i].split("</div>")[0]);
//                listLayout.add("best_topic_icon");
//            } else if (list[i].contains("\"review_image\">")) {//Изображение с текстом
//                listHtml.add(list[i].split("</div></div>")[0]);
//                listLayout.add("review_image");
//            } else if (list[i].contains("\"center\">")) {
//                listHtml.add(list[i].split("</div>")[0]);
//                listLayout.add("center");
//            } else if (list[i].contains("\"iframe_h\">")) {
//                listHtml.add(list[i].split("</div>")[0]);
//                listLayout.add("iframe_h");
//            } else if (list[i].contains("\"fotorama\"")) {
//                listHtml.add(list[i].split("\">")[1].split("</div>")[0]);
//                listLayout.add("fotorama");
//            } else if (list[i].contains("\"tags\"")) {//  ">
//                listHtml.add(list[i].split("\">")[1].split("</div>")[0]);
//                listLayout.add("tags");
//            } else if (list[i].contains("\"spoiler-title spoiler-arrow-close\">")) {
//                listHtml.add(list[i].split("</div>")[0]);
//                listLayout.add("spoiler-title");
//            } else if (list[i].contains("\"spoiler-body\">")) {
//                listHtml.add(list[i].split("</div>")[0]);
//                listLayout.add("spoiler-body");
//            } else if (list[i].contains("\"spoiler clear2\">") || list[i].contains("\"iframe_wrapper\"")) {
//                continue;
//            } else if (list[i].contains("\"right\">")) {
//                listHtml.add(list[i].split("</div>")[0]);
//                listLayout.add("right");
//            } else {
//            }
//            if (((list[i].split("(</div>)|(</p>)|(</iframe>)").length) > 1)) {//Переписать, и добавить, чтобы отображался тэн <hr>
//                String text = "";
//                for (int a = 1; a < list[i].split("</div>").length; a++) {
//                    text += list[i].split("</div>")[a];
//                }
//
//                if ((!text.trim().equals("") && !text.equals("   ") && !text.trim().equals("<br>") && !text.trim().equals("<br> \n<br>"))) {
//                    listHtml.add(text);
//                    listLayout.add("text");
//                }
//            }
//        }
    }

    private void pars(String html){
        listHtml = new ArrayList<>();
        listLayout = new ArrayList<>();

        List<String> list = new ArrayList<>();
        String[] strings = html.split("[\n\t]");

        if (!html.contains("<div ")){//Это для комментариев, по идеи должно сработать
            listHtml.add(html);
            listLayout.add("text");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        int numb = 0;
        for (int i=0;i<strings.length;i++){
            if (strings[i].contains("</div>")&&strings[i].contains("<div")&&numb==0){
                list.add(buffer.toString());
                buffer = new StringBuffer();
                if (strings[i+1].contains("<script>")){
                    list.add(strings[i]+"\n"+strings[i+1]);
                    i++;
                }else {
                    list.add(strings[i]);
                }
            }else if (strings[i].contains("<div")&&numb==0){
                list.add(buffer.toString());
                buffer = new StringBuffer();
                buffer.append(strings[i]).append("\n");
                numb++;
            }else if (strings[i].contains("<div")&&strings[i].contains("</div>")&&numb!=0){
                if (strings[i+1].contains("<script>")){
                    buffer.append(strings[i]).append("\n").append(strings[i+1]).append("\n");
                    i++;
                }else {
                    buffer.append(strings[i]).append("\n");
                }
            }else if (strings[i].contains("<div")&&numb!=0){
                buffer.append(strings[i]).append("\n");
                numb++;
            }else if (strings[i].contains("</div>")&&numb==1){
                buffer.append(strings[i]).append("\n");
                list.add(buffer.toString());
                buffer = new StringBuffer();
                numb--;
            }else if (strings[i].contains("</div>")&&numb>1){
                buffer.append(strings[i]).append("\n");
                numb--;
            }else {
                buffer.append(strings[i]).append("\n");
                if (strings.length-1==i)
                    list.add(buffer.toString());
            }
        }
        for (int i=0;i<list.size();i++){
            if (list.get(i).equals(""))continue;
            Document doc = Jsoup.parse("<body>"+list.get(i)+"</body>");
            Elements elements = doc.select("body > div");
            try {
                switch (elements.get(0).attr("class")){
                    case "best_topic_icon":
                        listHtml.add(list.get(i));
                        listLayout.add("best_topic_icon");
                        break;
                    case "spoiler clear2":
                        listHtml.add(list.get(i));
                        listLayout.add("spoiler-title");
                        break;
                    case "iframe_h":
                        listHtml.add(list.get(i));
                        listLayout.add("iframe_h");
                        break;
                    case "iframe_wrapper":
                        listHtml.add(list.get(i));
                        listLayout.add("iframe_h");
                        break;
                    case "review_image":
                        listHtml.add(list.get(i));
                        listLayout.add("review_image");
                        break;
                    default:
                        throw new IndexOutOfBoundsException();
                }
            }catch (IndexOutOfBoundsException e){

                if (list.get(i).contains("id=\"player_")){
                    System.out.println("begin\n"+list.get(i)+"\nend");
//                    System.out.println("wew 123\n"+list.get(i));//https://www.youtube.com/embed/A6XUVjK9W4o
                    String htmlVideo = "<div class=\"iframe_wrapper\">" +
                            "<div class=\"iframe_h\"><img class=\"iframe_ratio\" src=\""+list.get(i).split("poster: \"")[1].split("\"")[0]+"\">" +//Картинка
                            "<iframe src=\""+(list.get(i).split("file:\"")[1].split("\"")[0]).replace("watch?v=", "embed/")
                            +"\" frameborder=\"0\" allowfullscreen>" +//Видео   embed/
                            "</iframe>" +
                            "</div>" +
                            "</div>";
                    listHtml.add(htmlVideo);
                    listLayout.add("iframe_h");
                }else {
                    listHtml.add(list.get(i));
                    listLayout.add("text");
                }
            }
        }
    }
}
