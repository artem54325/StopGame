package ru.stopgame.artem.stopgame.parsers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import ru.stopgame.artem.stopgame.models.Comment;
import ru.stopgame.artem.stopgame.models.DialogPostModel;
import ru.stopgame.artem.stopgame.models.GameTable;
import ru.stopgame.artem.stopgame.models.MenuBaseItem;
import ru.stopgame.artem.stopgame.models.PostShow;

public class PagePostParser {//Переписать парксер ЧТобы был как NEWSPARS

    public static PostShow parsShow(String html){
        PostShow object = new PostShow();
        Document doc = Jsoup.parse(html);
        if (!doc.select("div.game-image").isEmpty()) {
            object.setGameTable(parsGameTable(doc.select("div.section-game-main").first()));
//            if (doc.select(""))//https://www.youtube.com/watch    <div class="iframe_h">
            String htmlMain;//.html().split("</table>")[1].split("</div>")[1]
            if (!doc.select("div.main_text").isEmpty()) htmlMain = doc.select("div.main_text").first().html();//fotorama__nav-wrap
            else htmlMain = doc.select("div.media-content").first().html();//fotorama__nav-wrap

            if (!doc.select("div.lent-left>div.iframe_wrapper").isEmpty()){
                htmlMain=doc.select("div.lent-left>div.iframe_wrapper").html()+"\n"+htmlMain;
            }
            object.setText(htmlMain);
        }else if (doc.select("div.section.section-general-title").size()==2){
            if (!doc.select("div.section.section-general-title").isEmpty()){
                object.setTitlePost(doc.select("div.section.section-general-title").get(1).text());
            }else{
                object.setTitlePost(doc.select("div.title-categories").first().text());
            }

            String htmlMain = null;
            if (doc.select("div.main_text").select("div.user-info").isEmpty()){
                try {
                    htmlMain = doc.select("div.main_text").html().split("</table>")[1];
                }catch (ArrayIndexOutOfBoundsException e){
                    htmlMain = doc.select("div.main_text").first().html();
                }
            }else{
                htmlMain = doc.select("div.main_text").first().html();
            }
            if (!doc.select("div.lent-left>div.iframe_wrapper").isEmpty()){
                htmlMain=doc.select("div.lent-left>div.iframe_wrapper").html()+"\n"+htmlMain;
            }
            object.setText(htmlMain);
        }else {
            object.setTitlePost(doc.select("div.title-categories").first().text());
            object.setText((doc.select("div.blog-block").html()));
        }
//        if(!doc.select("div.lent-left").select("div.iframe_h").isEmpty()){
////                &&doc.html().contains(".mp4\"")){
//            System.out.println(doc.select("div.lent-left .iframe_h").html());
//            object.setAudioMedia(doc.select("div.lent-left").select("div.iframe_h").html());
//        }else
        if (doc.html().contains("var player = new Playerjs({id:\"")&&!doc.select("div.lent-left").select("div.iframe_h").isEmpty()){//&&!doc.html().contains(".mp4\"")
            object.setAudioMedia(doc.html().split("var player = new Playerjs")[1].split("</script>")[0]);
        }
        object.setDate(doc.select("div.response-date").first().text());//html.split("<span class=\"glyphicons glyphicons-calendar\"></span>")[1].split("</div>")[0]
        object.setComments(doc.select("div.lent-comments").first().text());//html.split("<span class=\"glyphicons glyphicons-comments\"></span>")[1].split("</div>")[0]
        object.setName(doc.select("div.pubinfo-name").text());//html.split("<div class=\"title\">")[2].split("</div>")[0]
        object.setPriting(doc.select("div.print-link").select("a").attr("href"));//html.split("<span class=\"glyphicons glyphicons-print\"></span>")[1].split("</div>")[0]
        object.setViewUrl(doc.select("div.popup").select("img").attr("src"));//print-link
        object.setViews(doc.select("div.lent-views").text());//html.split("<span class=\"glyphicons glyphicons-eye-open\"></span>")[1].split("</div>")[0]


        int app = 0;
        String[] list= doc.select("div.stop_rate").html().split("https://images.stopgame.ru/site/icons/rating");
        for (int i=1;i<list.length;i++){
            if (list[i].contains("-170-"))
                app=i;
        }

        object.setAppraisal(app);
        object.setCommentsList(parsComments(doc.select("div.section-comments")));

        return object;
    }

    public static PostShow parsNews(String html){
        PostShow object = new PostShow();
        Document doc = Jsoup.parse(html);
        if (!doc.select("div.game-image").isEmpty()) {
            object.setGameTable(parsGameTable(doc.select("div.section-game-main").first()));
        }
        if (!doc.select("div.title-categories").isEmpty()) object.setTitlePost(doc.select("div.title-categories").first().text()+"<br><strong>" + doc.select("span.bread-child").text()+"</strong>");
        object.setText(doc.select("div.main_text").first().html());

        object.setDate(doc.select("div.response-date").first().text());
        object.setComments(doc.select("div.lent-comments").first().text());
//        try {
            object.setName(doc.select("div.pubinfo-name").first().text());
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }

        object.setPriting(doc.select("div.print-link").select("a").attr("href"));
        object.setViewUrl(doc.select("div.popup").select("img").attr("src"));
        object.setViews(doc.select("div.lent-views").text());


        object.setTag(doc.select("div.tags").html());
        object.setCommentsList(parsComments(doc.select("div.section-comments")));

        return object;
    }

    public static List<MenuBaseItem> getTabMenus(Elements elements){
        List<MenuBaseItem> list = new ArrayList<>();
        elements = elements.select("li");
        int a=1;
        if (elements.size()==2){
            a=0;
        }
        for (int i=a;i<elements.size();i++){
            MenuBaseItem item = new MenuBaseItem();
            if (elements.get(i).toString().contains("active"))item.setActive(true);
            item.setUrl(elements.get(i).select("a").attr("href"));
            item.setName(elements.get(i).select("a").text() + (!elements.get(i).select("sup").isEmpty()?" ("+elements.get(i).select("sup").text()+")":""));//
            list.add(item);
        }
        return list;
    }


    public static List<Object> parsGame(String html) {
        List<Object> list = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        if (!doc.select("div.game-image").isEmpty()) {
            list.add(parsGameTable(doc.select("div.section-game-main").first()));
        }else list.add(null);

        if (!doc.select("div.tab-menus.tab-adaptive.tab-game").isEmpty()){
            list.add(getTabMenus(doc.select("div.tab-menus.tab-adaptive.tab-game")));
        }else list.add(null);

        try {
            list.addAll(PageNewsParser.getList(doc.select("div.lent-block")));
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return list;
    }

    public static PostShow parsBlogs(String html){
        PostShow object = new PostShow();
        Document doc = Jsoup.parse(html);

        object.setTitlePost(doc.select("div.title-categories").first().text()+"<br><strong>" + doc.select("span.bread-child").text()+"</strong>");

        if (!doc.select("div.topic_text").isEmpty()){
            object.setText(doc.select("div.topic_text").first().html());//ПЕреписать
        }else{
            //section-general-title
            object.setText(doc.select("div.section-general-title").first().html());//ПЕреписать
        }



        object.setDate(doc.select("div.response-date").first().text());
        object.setComments(doc.select("div.lent-comments").first().text());
        object.setName(doc.select("div.user-name").first().text());
        object.setPriting(doc.select("div.print-link").select("a").attr("href"));
        object.setViewUrl(doc.select("div.popup").select("img").attr("src"));
        object.setViews(doc.select("div.lent-views").text());

        object.setTag(doc.select("div.tags").html());

        object.setCommentsList(parsComments(doc.select("div.section-responses")));

        return object;
    }

    public static DialogPostModel getBut(String html){
        DialogPostModel model = new DialogPostModel();
        Document doc = Jsoup.parse(html);

        try {//Надо бы ещё сделать ссылку. Чтобы по нажатию переходила, для установки оценки и тд...
            model.setVisitorsValue(doc.select("span.rating-mark").html().split("<sup>")[0]);
            if (doc.select("span.rating-mark").html().split("<sup>").length>1) model.setVisitorsNumber(doc.select("span.rating-mark").html().split("<sup>")[1].split("</sup>")[0]);
            model.setVisitorsColor(doc.select("div.user-ratings").attr("background"));
            model.setFollowNumber(doc.select("span.game-favourite-count").text());//list.add(getTabMenus(doc.select("div.tab-menus.tab-adaptive.tab-game")));
            model.setTabsMenu(getTabMenus(doc.select("div.tab-menus.tab-adaptive.tab-game")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return model;
    }

    private static List<Comment> parsComments(Elements element){
        List<Comment> list = new ArrayList<>();


        Elements elements = element.select("table").attr("width","100%");//
        for (Element e : elements){//int i=0;i<e.size();i++
            Comment comments = new Comment();//itemscope="comment"

            if (e.select("td.comment_level_icons").isEmpty())//Убираем лищний td
                continue;

            comments.setUrlImage(e.select("div.user-image").select("img").attr("src"));
            comments.setOnline(e.select("span.user_online").hasAttr("title") ? true : false);//ВОзможно переписать
            comments.setUserName(e.select("div.user-name").select("a").text());
            comments.setUserUrl(e.select("div.user-name").select("a").attr("href"));
            comments.setRaiting(e.select("div.vote-result.colored-bg-gray").text());
            comments.setDate(e.select("div.response-date").text());
            try {
                comments.setAnswering(e.select("div.reply.hide").attr("id").split("_")[1]);//answering
            }catch (ArrayIndexOutOfBoundsException ee){
                ee.printStackTrace();
            }
            try {
                comments.setType(e.select("div.vote-minus").attr("onclick").split("this, '")[1].split("',")[0]);
            }catch (ArrayIndexOutOfBoundsException e1){
                e1.printStackTrace();
                continue;
            }

            comments.setText(e.select("div.main_text.response-body").text());

            try {
                comments.setNumberComm(Integer.parseInt(e.select("td.comment_level_icons").attr("style").split("width:")[1].split("px;")[0])/19);//style="width:19px;"><div>
            }catch (ArrayIndexOutOfBoundsException ee){
                ee.printStackTrace();
            }
            list.add(comments);
        }

        return list;
    }

    private static GameTable parsGameTable(Element element){
        GameTable object = new GameTable();

        object.setUrlView(element.select("div.game-image").select("img").attr("src"));//html.split("<div class=\"game-image\">")[1].split("<img src=\"")[1].split("\"")[0]
        object.setDevelope(String.valueOf(element.select("div.game-about").get(0)).split("</span>")[1]);//html.split("<div class=\"game-about\"><span class=\"par\">Разработчик: </span>")[1].split("</div>")[0]
        object.setGame(String.valueOf(element.select("div.game-about").get(1)).split("</span>")[1]);//html.split("<div class=\"game-about game-about-title\"><span class=\"par\">Игра:</span>")[1].split("")[0]
        object.setPlatform(String.valueOf(element.select("div.game-about").get(2)).split("</span>")[1]);//html.split("<div class=\"game-about\"><span class=\"par\">Платформа: </span>")[1].split("</div>")[0]
        object.setPublisher(String.valueOf(element.select("div.game-about").get(3)).split("</span>")[1]);//html.split("<div class=\"game-about\"><span class=\"par\">Издатель: </span>")[1].split("</div>")[0]
        try {
            object.setRelease(String.valueOf(element.select("div.game-about").get(4)).split("</span>")[1]);//html.split("<div class=\"game-about\"><span class=\"par\">Дата выхода: </span>")[1].split("</div>")[0]
        }catch (ArrayIndexOutOfBoundsException e){
            object.setRelease("");
        }
        if (element.select("div.game-about").size()>5) object.setTedxtOfficale(String.valueOf(element.select("div.game-about").get(5)));

        return object;
    }
}
