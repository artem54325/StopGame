package ru.stopgame.artem.stopgame.parsers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import ru.stopgame.artem.stopgame.models.ImgAndUrl;
import ru.stopgame.artem.stopgame.models.MenuBaseItem;
import ru.stopgame.artem.stopgame.models.NewsItem;
import ru.stopgame.artem.stopgame.models.OnlineAnnouncer;

public class PageNewsParser {
    public static List<MenuBaseItem>parserMenu(String html){
        List<MenuBaseItem> list = new ArrayList<>();
        Document doc = Jsoup.parse(html);//table.menu-div
        Elements elements = doc.select("table.menu-div");

        for (Element elem :elements.select("td.menu-cell")){
            String url = elem.select("a.menu-link").attr("href");
            MenuBaseItem baseItem = new MenuBaseItem();
            baseItem.setName(elem.select("a.menu-link").text());
            if (!url.equals("#"))
                baseItem.setUrl(url);

            for (Element element2: elem.select("a.menu-drop-link")){
                baseItem.getArray().add(new MenuBaseItem(element2.text(),
                        element2.attr("href")));
            }
            list.add(baseItem);
        }
        return list;
    }

    public static List<Object> parser(String html){
        List<Object> list = new ArrayList<>();

        Document doc = Jsoup.parse(html);

        //tab-menus  switcher3
        if (!doc.select("ul.switcher3").isEmpty()) list.add(PagePostParser.getTabMenus(doc.select("ul.switcher3")));

        //tab-menus  switcher1
        if (!doc.select("div.tab-menus").isEmpty()&&doc.select("div.tab-menus").size()==2)list.add(PagePostParser.getTabMenus(doc.select("div.tab-menus").get(1).getAllElements()));

        list.add(getCaption(doc.select("h1").text()));

        if (!doc.select("div.big-announcer").isEmpty()) list.add(getHeadImgAnd(doc.select("div.big-announcer")));

        if (!doc.select("div.live_on_main").isEmpty()) list.add(getLiveTwitch(String.valueOf(doc.select("div.live_on_main").first())));//ДОБАВИТЬ STOPGAME LIVE

        if (!doc.select("h2").isEmpty())list.add(getCaption(doc.select("h2").first().text()));

        if (!doc.select("div.online-announcer").isEmpty()) list.add(getStopgameLive(doc.select("div.online-announcer").first()));

        if (doc.select("h2").size()>1) list.add(getCaption(doc.select("h2").get(1).text()));//lent-block news-lent

        if (!doc.select("div#faq-show").isEmpty()){
            list.addAll(getListFaq(doc.select("div.section-news-lent")));
        }else{
            list.addAll(getList(doc.select("div.section-news-lent")));
        }

        list.add(getCaption(doc.select("h3").text()));


        return list;
    }

    private static List<NewsItem> getListFaq(final Elements elements) {
        System.out.println("WEWWW");
        List<NewsItem> list = new ArrayList<>();
        Elements elements2 = elements.select("div.blog-block");
        for (Element post : elements2) {
            NewsItem holder = new NewsItem();
            if (!post.select("span.tag").text().isEmpty() && !post.select("div.img-tag").isEmpty()) {
                holder.setTag(post.select("div.img-tag").first().select("span.tag").text());
                if (holder.getTag().equals("блоги")) {
                    holder.setColorTag("#009AFB");//
                } else {
                    holder.setColorTag("#11AE46");//
                }
            } else if (!post.select("span.tag").text().isEmpty() && !post.select("div.img-tag-rating").isEmpty()) {
                holder.setTag(post.select("div.img-tag-rating").first().select("span.tag").text());
                if (holder.getTag().equals("блоги")) {
                    holder.setColorTag("#009AFB");//
                } else {
                    holder.setColorTag("#11AE46");//
                }
            }
            if (!post.select("div.main_text").select("div.media-faq").isEmpty()){
                System.out.println("wew");
                holder.setText(post.select("div.main_text").html().split("</table>")[1].split("</div>")[1]);
            }else {
                holder.setText(post.select("div.main_text").html().split("</table>")[1]);//.split("</table>")[1]   post.children();
            }
            holder.setNamePost(post.select("a.blog-title").text());
            holder.setKomments(post.select("div.lent-comments").text());
            holder.setDate(post.select("div.lent-date").text());
            holder.setImgUrl(post.select("img.faq_avatar").attr("src"));
            holder.setUrl(post.select("a").attr("href"));

            if (holder.getText().isEmpty() && holder.getNamePost().isEmpty())
                continue;
            list.add(holder);
        }
        return list;
    }

    private static ArrayList<OnlineAnnouncer> getStopgameLive(Element element){
        ArrayList<OnlineAnnouncer> list = new ArrayList<>();
        for (Element elImage:element.select("li")){
            OnlineAnnouncer object = new OnlineAnnouncer();

            object.setUrlImage(elImage.select("img").attr("src"));
            object.setUrlLink(elImage.select("a").attr("href"));

            list.add(object);
        }
        return list;
    }

    private static String getCaption(String html){
        return html;
    }

    public static List<NewsItem> getList(final Elements elements){
        List<NewsItem> list = new ArrayList<>();
        Elements elements2 = elements.select("div.lent-block");
        if (elements2.size()!=0) {
            for (Element post : elements2) {
                NewsItem holder = new NewsItem();
                if (!post.select("span.tag").text().isEmpty() && !post.select("div.img-tag").isEmpty()) {
                    holder.setTag(post.select("div.img-tag").first().select("span.tag").text());
                    if (holder.getTag().equals("блоги")) {
                        holder.setColorTag("#009AFB");//
                    } else {
                        holder.setColorTag("#11AE46");//
                    }
                } else if (!post.select("span.tag").text().isEmpty() && !post.select("div.img-tag-rating").isEmpty()) {
                    holder.setTag(post.select("div.img-tag-rating").first().select("span.tag").text());
                    if (holder.getTag().equals("блоги")) {
                        holder.setColorTag("#009AFB");//
                    } else {
                        holder.setColorTag("#11AE46");//
                    }
                }
                holder.setText(post.select("div.brief").text());
                holder.setNamePost(post.select("div.lent-title").text());
                holder.setKomments(post.select("div.lent-comments").text());
                holder.setDate(post.select("div.lent-date").text());
                holder.setImgUrl(post.select("img").attr("src"));
                holder.setUrl(post.select("a").attr("href"));

                if (holder.getText().isEmpty() && holder.getNamePost().isEmpty())
                    continue;
                list.add(holder);
            }
        }else{
            elements2 = elements.select("div.blog-block");
            for (Element post : elements2) {
                NewsItem holder = new NewsItem();
                if (!post.select("div.vote-result").isEmpty()) {
                    try {
                        holder.setTag(post.select("div.vote-result").first().text());
                        int raiting = Integer.parseInt(holder.getTag());
                        if (raiting == 0) {
                            holder.setColorTag("#d2d2d2");
                        } else if (raiting > 0) {
                            holder.setColorTag("#11AE46");
                        } else {
                            holder.setColorTag("#fb0000");
                        }
                    } catch (Exception e) {
                        holder.setColorTag("#11AE46");
                        e.printStackTrace();
                    }
                }

                holder.setText(post.select("div.main_text").text());
                holder.setNamePost(post.select("div.blog-title").text());
                holder.setKomments(post.select("div.lent-comments").text());
                holder.setDate(post.select("div.response-date").text());
                holder.setImgUrl(post.select("div.media").select("img").attr("src"));
                holder.setUrl(post.select("td.title").select("a").attr("href"));
                holder.setView(post.select("div.lent-views").text());
                holder.setRating(post.select("div.fav-topic").text());

                if (holder.getText().isEmpty() && holder.getNamePost().isEmpty())
                    continue;
                list.add(holder);
            }
        }
        return list;
    }
    private static Object getLiveTwitch(String html){
        String[] massLiveTwitch = new String[2];
        massLiveTwitch[0]=html.split("<div class=\"main_text live_on_main\">")[1].split("<br>")[0];//Текст
        massLiveTwitch[1]=html;//Ссылка на ютуб
        return massLiveTwitch;
    }

    private static List<ImgAndUrl> getHeadImgAnd(Elements elements){
        List<ImgAndUrl> list = new ArrayList<>();
        elements = elements.select("div.fotorama").select("div");
        for (Element image:elements){
            ImgAndUrl object = new ImgAndUrl();

            object.setUrlImage(image.attr("data-img"));
            object.setUrlLink(image.select("a").attr("href"));

            if (object.getUrlImage().isEmpty()||object.getUrlLink().isEmpty()) continue;

            list.add(object);
        }
        return list;
    }
}