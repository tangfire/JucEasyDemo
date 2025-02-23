package com.fire.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FinalCommentGenerator {
    // 情感分布比例（积极70%，中性25%，消极5%）
    private static final double POSITIVE_RATIO = 0.70, NEUTRAL_RATIO = 0.29;

    // 分层关键词库（严格逻辑校验）
    private static final Map<String, List<String>> KEYWORDS = Map.of(
            // 文化体验类（主词+修饰词组合）
            "文化主体", Arrays.asList("篝火晚会", "毕摩祈福", "长街宴", "非遗展示", "羊皮鼓舞", "彝族歌舞", "传统手工艺", "民俗表演", "火把游行"),
            "文化修饰", Arrays.asList("仪式感", "文化内涵", "艺术价值", "互动设计", "沉浸式体验", "独特魅力", "历史底蕴", "民族特色", "视觉盛宴"),

            // 设施服务类（设施+评价）
            "设施主体", Arrays.asList("卫生条件", "停车场", "医疗站", "导览系统", "接驳车", "休息区", "餐饮服务", "购物商店", "信息中心"),
            "设施评价", Arrays.asList("干净整洁", "指引清晰", "响应迅速", "信息准确", "班次密集", "设施齐全", "服务周到", "方便快捷", "井然有序"),

            // 问题类（精准对应关系）
            "问题类型", Arrays.asList("黄牛票", "安全隐患", "物价虚高", "交通拥堵", "服务态度差", "设施老旧", "信息不透明", "卫生问题"),
            "问题表现", Arrays.asList("加价倒卖", "缺乏防护", "翻倍收费", "排队过长", "态度恶劣", "设备损坏", "误导信息", "垃圾遍地")
    );

    // 逻辑化模板库（主谓宾结构）
    private static final Map<String, String[]> TEMPLATES = Map.of(
            "正面", new String[]{
                    "今年火把节的{}让人印象深刻！{}与{}相得益彰，真正展现了彝族文化的{}。",
                    "强烈推荐{}！{}的{}和{}的{}堪称完美结合。",
                    "{}的{}让人耳目一新，尤其是{}的{}更是锦上添花。"
            },
            "中性", new String[]{
                    "{}的{}达到预期，但{}的{}仍需改进。",
                    "如果是体验{}，{}值得一看，但{}的{}令人遗憾。",
                    "{}的{}中规中矩，{}的{}有待提升。"
            },
            "负面", new String[]{
                    "门票{}元完全不合理！{}的{}问题（如{}）严重破坏体验。",
                    "所谓的{}名不副实，{}的{}问题突出，{}！",
                    "{}的{}令人失望，{}的{}问题亟待解决。"
            }
    );

    // 增强版语义校验规则（新增type参数）
    private static boolean isValidCombination(String type, String template, List<String> words) {
        // 文化组合校验
        if (template.contains("与")) {
            return (words.get(0).equals("篝火晚会") && words.get(1).matches("长街宴|火把游行")) ||
                    (words.get(0).equals("毕摩祈福") && words.get(1).matches("羊皮鼓舞|彝族歌舞")) ||
                    (words.get(0).equals("非遗展示") && words.get(1).matches("传统手工艺|民俗表演"));
        }

        // 负面评论精准对应
        if ("负面".equals(type)) {
            Map<String, String> problemMapping = Map.of(
                    "黄牛票", "加价倒卖", "安全隐患", "缺乏防护",
                    "物价虚高", "翻倍收费", "交通拥堵", "排队过长",
                    "服务态度差", "态度恶劣", "设施老旧", "设备损坏",
                    "信息不透明", "误导信息", "卫生问题", "垃圾遍地"
            );
            if (words.size() >= 2) {
                return problemMapping.getOrDefault(words.get(0), "").equals(words.get(1));
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (FileWriter writer = new FileWriter("final_comments.txt")) {
            Random random = new Random();
            for (int i = 0; i < 500; i++) {
                String type = getCommentType(random);
                String comment = generateComment(type, random);
                if (!comment.contains("null")) {
                    writer.write(comment + "\n");
                }
            }
        }
    }

    // 新增：规范情感类型判断逻辑
    private static String getCommentType(Random random) {
        double ratio = random.nextDouble();
        if (ratio < POSITIVE_RATIO) return "正面";
        if (ratio < POSITIVE_RATIO + NEUTRAL_RATIO) return "中性";
        return "负面";
    }

    private static String generateComment(String type, Random random) {
        String template = TEMPLATES.get(type)[random.nextInt(TEMPLATES.get(type).length)];
        List<String> words = new ArrayList<>();
        Set<String> used = new HashSet<>();
        int maxAttempts = 20;
        boolean isValid = false;

        int attempts = 0;
        do {
            words.clear();
            used.clear();
            for (int i = 0; i < template.split("\\{\\}").length - 1; i++) {
                String category = getCategory(type, i);
                String word = generateValidWord(category, used, random);
                words.add(word);
                used.add(word);
            }
            isValid = isValidCombination(type, template, words);
            attempts++;
        } while (!isValid && attempts < maxAttempts);

        // ================== 关键修复部分 ==================
        // 处理门票价格（修复重复"元元"和占位符残留问题）
        if (template.contains("门票")) {
            // 步骤1: 替换第一个{}为%d元（注意模板原始内容）
            String formattedTemplate = template.replaceFirst("\\{\\}", "%d元");

            // 步骤2: 替换剩余{}为%s
            formattedTemplate = formattedTemplate.replace("{}", "%s");

            // 步骤3: 构建参数数组（价格 + 所有关键词）
            Object[] params = new Object[words.size() + 1];
            params[0] = 80 + random.nextInt(150); // 价格80-230元
            System.arraycopy(words.toArray(), 0, params, 1, words.size());

            return String.format(formattedTemplate, params);
        }
        // ================================================

        // 常规评论处理
        return String.format(template.replace("{}", "%s"), words.toArray());
    }

    // 新增：分类选择逻辑
    private static String getCategory(String type, int index) {
        return switch (type) {
            case "正面" -> (index % 2 == 0) ? "文化主体" : "文化修饰";
            case "中性" -> (index < 2) ? "设施主体" : "设施评价";
            case "负面" -> (index % 2 == 0) ? "问题类型" : "问题表现";
            default -> "文化主体";
        };
    }

    // 新增：带重试机制的词汇生成
    private static String generateValidWord(String category, Set<String> used, Random random) {
        int maxAttempts = 50;
        String word;
        do {
            word = KEYWORDS.get(category).get(random.nextInt(KEYWORDS.get(category).size()));
            if (--maxAttempts <= 0) break;
        } while (used.contains(word));
        return word;
    }

    // 新增：格式化含门票价格的评论
    private static String formatTicketComment(String template, Random random, List<String> words) {
        int price = 80 + random.nextInt(150); // 80-230元
        Object[] params = new Object[words.size() + 1];
        params[0] = price;
        System.arraycopy(words.toArray(), 0, params, 1, words.size());
        return String.format(template.replaceFirst("\\{\\}", "%d元"), params);
    }
}