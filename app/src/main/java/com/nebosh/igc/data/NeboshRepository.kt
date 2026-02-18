package com.nebosh.igc.data

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow

class NeboshRepository(private val neboshDao: NeboshDao) {

    val allSyllabusElements: Flow<List<SyllabusElement>> = neboshDao.getAllSyllabusElements()
    val allGlossaryTerms: Flow<List<GlossaryTerm>> = neboshDao.getAllTerms()

    fun getTopicsByElement(elementId: String): Flow<List<Topic>> {
        return neboshDao.getTopicsByElement(elementId)
    }

    suspend fun getTopicById(topicId: String): Topic? {
        return neboshDao.getTopicById(topicId)
    }

    suspend fun getQuestionsByTopic(topicId: String): List<Question> {
        return neboshDao.getQuestionsByTopic(topicId)
    }
    // 1. ANA VERİ EKLEME YÖNETİCİSİ
    suspend fun insertSampleData() {
        // A) Önce Ana Üniteleri Tanımla
        neboshDao.insertSyllabusElement(SyllabusElement("IG1", "IG1: İSG Yönetimi", "IG1: Management of H&S", "IG1: Management", "IG1: Zarządzanie", "Element 1-4", "Elements 1-4", "Elemente 1-4", "Elementy 1-4"))
        neboshDao.insertSyllabusElement(SyllabusElement("IG2", "IG2: İşyerinde Risklerin Kontrolü", "IG2: Controlling Workplace Risks", "IG2: Arbeitsrisiken", "IG2: Ryzyko w miejscu pracy", "Element 5-11", "Elements 5-11", "Elemente 5-11", "Elementy 5-11"))
        neboshDao.insertSyllabusElement(SyllabusElement("IG2_PRATIK", "IG2: Pratik Risk Değerlendirmesi", "IG2: Practical Risk Assessment", "IG2: Praktische Risikobeurteilung", "IG2: Praktyczna Ocena Ryzyka", "Proje Rehberi", "Project Guide", "Projektleitfaden", "Przewodnik"))
        // B) Sonra Alt Dersleri Sırayla ÇAĞIR
        insertElement1Topics()
        insertElement1Topic2()
        insertElement1Topic3()
        insertElement2Topics()
        insertElement3Topics()
        insertElement3Topic2()
        insertElement3Topic3()
        insertElement3Topic4()
        insertElement3Topic5()
        insertElement3Topic6()
        insertElement3Topic7()
        insertElement4Topics()
        insertElement4Topic2()
        insertElement4Topic3()
        insertElement4Topic4()
        insertElement5Topics()
        insertElement5Topic2()
        insertElement5Topic3()
        insertElement5Topic4()
        insertElement5Topic5()
        insertElement6Topics()
        insertElement6Topic2()
        insertElement6Topic3()
        insertElement7Topics()
        insertElement7Topic2()
        insertElement7Topic3()
        insertElement7Topic4()
        insertElement8Topics()
        insertElement8Topic2()
        insertElement8Topic3()
        insertElement8Topic4()
        insertElement8Topic5()
        insertElement8Topic6()
        insertElement9Topics()
        insertElement9Topic2()
        insertElement9Topic3()
        insertElement9Topic4()
        insertElement10Topics()
        insertElement10Topic2()
        insertElement10Topic3()
        insertElement10Topic4()
        insertElement11Topics()
        insertElement11Topic2()
        insertElement12Topics()
        insertSampleQuestions()
        insertGlossaryTerms()
    }
    // --- ELEMENT 12: PRATİK PROJE REHBERİ (ZENGİNLEŞTİRİLMİŞ İÇERİK) ---
    private suspend fun insertElement12Topics() {
        // KONU 12.1: GİRİŞ VE METODOLOJİ
        val lesson12_1 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Sınavın Yapısı", "Assessment Structure", "Prüfungsstruktur", "Struktura Egzaminu"),
                    content = LocalizedText(
                        tr = "IG2, işyerinizde yapacağınız gerçek bir risk değerlendirmesidir. 4 aşamadan oluşur:\n\n1. İşyeri Tanımı (Description)\n2. Risk Değerlendirmesi (Risk Assessment)\n3. Öncelikli 3 Eylem (Prioritised Actions)\n4. İzleme ve Gözden Geçirme (Review)\n\nNOT: Bu sınavda puan yoktur, 'Geçti/Kaldı' (Pass/Refer) sistemi vardır. Tek bir kriteri bile atlarsanız kalırsınız.",
                        en = "IG2 is a real workplace risk assessment. 4 stages:\n1. Description\n2. Assessment\n3. 3 Actions\n4. Review\nGraded as Pass/Refer. Missing one criteria leads to failure.",
                        de = "4 Stufen: Beschreibung, Bewertung, 3 Maßnahmen, Überprüfung. Pass/Fail Bewertung.",
                        pl = "4 etapy: Opis, Ocena, 3 Działania, Przegląd. Ocena Zdał/Nie zdał."
                    ),
                    imageType = "pdca"
                ),
                LessonSection(
                    title = LocalizedText("Bölüm 1: Metodoloji", "Part 1: Methodology", "Teil 1: Methodik", "Część 1: Metodologia"),
                    content = LocalizedText(
                        tr = "Raporun başında 'Bilgiyi nasıl topladığınızı' kanıtlamalısınız. Şunları mutlaka yazın:\n\n- Kaynaklar: 'HSE web sitesini inceledim', 'ILO sözleşmelerine baktım'.\n- İletişim: 'Çalışanlarla ve İSG temsilcisiyle görüştüm'.\n- Belgeler: 'Kaza kayıtlarını ve bakım raporlarını inceledim'.\n- Gözlem: 'Sahayı dolaşarak fiziksel tehlikeleri not aldım'.",
                        en = "You must prove how you gathered info:\n- Sources: 'Checked HSE website/ILO'.\n- People: 'Spoke to workers/reps'.\n- Docs: 'Reviewed accident books/maintenance logs'.\n- Observation: 'Walked the site'.",
                        de = "Methodik beweisen: Quellen (HSE/ILO), Gespräche, Dokumentenprüfung, Begehung.",
                        pl = "Udowodnij metodologię: Źródła (HSE/ILO), Rozmowy, Dokumenty, Obserwacja."
                    ),
                    imageType = "audit"
                )
            )
        )
        neboshDao.insertTopic(Topic("12.1", "IG2_PRATIK", "1. Giriş ve Metodoloji", "1. Intro & Methodology", "1. Einführung & Methodik", "1. Wstęp i Metodologia", Gson().toJson(lesson12_1)))

        // KONU 12.2: RİSK DEĞERLENDİRMESİ (TABLO)
        val lesson12_2 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Altın Kural: 10 Tehlike", "Golden Rule: 10 Hazards", "Goldene Regel: 10 Gefahren", "Złota Zasada: 10 Zagrożeń"),
                    content = LocalizedText(
                        tr = "Tablonuzun geçerli olması için:\n\n1. En az 10 FARKLI tehlike bulun.\n2. Bu tehlikeler en az 5 FARKLI kategoriden gelmeli (Örn: Yangın, Elektrik, Yüksekte Çalışma, Kimyasallar, Ergonomi).\n3. Sadece 'Kayma/Takılma' yazıp geçmeyin, çeşitlilik şarttır.",
                        en = "For a valid table:\n1. Find at least 10 DIFFERENT hazards.\n2. From at least 5 DIFFERENT categories (Fire, Electric, Height, Chemicals, Ergonomics).\n3. Variety is key.",
                        de = "Mindestens 10 Gefahren aus 5 verschiedenen Kategorien.",
                        pl = "Minimum 10 zagrożeń z 5 różnych kategorii."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("Tabloyu Doldurma (Sütunlar)", "Filling the Columns", "Spalten ausfüllen", "Wypełnianie Kolumn"),
                    content = LocalizedText(
                        tr = "Her satırda şu detaylar olmalı:\n\n- Tehlike Nedir?: 'Atölye zeminine dökülmüş yağ'.\n- Kim Zarar Görür?: 'Operatörler, temizlikçiler ve oradan geçen ziyaretçiler'.\n- Nasıl Zarar Görür?: 'Kayarak düşebilir, kolunu kırabilir veya başını çarpabilir'.\n- Mevcut Önlemler: 'Şu an sadece uyarı levhası var' (Yetersiz olduğunu belirtin).",
                        en = "Details required:\n- What is the hazard? (Oil spill).\n- Who? (Operators, cleaners, visitors).\n- How? (Slip, break arm, head injury).\n- Current Controls? (Only a sign - state it is insufficient).",
                        de = "Gefahr, Wer/Wie betroffen, Aktuelle Kontrollen (Lücken aufzeigen).",
                        pl = "Zagrożenie, Kto/Jak, Obecne kontrole (Wskaż braki)."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("Ek Önlemler ve Hiyerarşi", "Further Actions & Hierarchy", "Weitere Maßnahmen", "Dalsze Działania"),
                    content = LocalizedText(
                        tr = "'Ek Önlemler' sütununu doldururken Kontrol Hiyerarşisine (ERIC PD) uyun:\n\n1. Eliminasyon: Yağ kaçağını tamir et.\n2. Mühendislik: Damlama tavası koy.\n3. İdari: Temizlik prosedürü oluştur.\n4. KKD: Kaymaz ayakkabı ver (En son çare!).\n\nHer eylem için Sorumlu Kişi (Ünvan) ve Termin Süresi (Gerçekçi tarih) yazın.",
                        en = "Use Hierarchy of Control (ERIC PD):\n1. Eliminate (Fix leak).\n2. Engineering (Drip tray).\n3. Admin (Procedure).\n4. PPE (Boots - Last resort!).\nInclude Responsible Person & Deadline.",
                        de = "Hierarchie beachten (Eliminierung vor PSA). Verantwortliche und Fristen setzen.",
                        pl = "Hierarchia (Eliminacja przed ŚOI). Osoba odpowiedzialna i termin."
                    ),
                    imageType = "pdca"
                )
            )
        )
        neboshDao.insertTopic(Topic("12.2", "IG2_PRATIK", "2. Risk Değerlendirmesi", "2. Risk Assessment", "2. Risikobeurteilung", "2. Ocena Ryzyka", Gson().toJson(lesson12_2)))

        // KONU 12.3: RAPORLAMA VE YÖNETİMİ İKNA
        val lesson12_3 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("3 Öncelikli Eylem", "3 Prioritised Actions", "3 Priorisierte Maßnahmen", "3 Priorytetowe Działania"),
                    content = LocalizedText(
                        tr = "Tablonuzdan en acil ve önemli 3 tehlikeyi seçin. Bunlar için yönetime ayrı bir rapor yazacaksınız. Genellikle 'Yüksek Riskli' olanları seçin (Örn: Pres makinesinde el sıkışması, Forklift kazası).",
                        en = "Select top 3 urgent hazards from your table. Write a separate report for these. Choose High Risk items (e.g., machine guarding, forklift).",
                        de = "Wähle die 3 wichtigsten Gefahren für den Bericht (Hohes Risiko).",
                        pl = "Wybierz 3 najważniejsze zagrożenia do raportu (Wysokie Ryzyko)."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("Ahlaki ve Yasal Gerekçe", "Moral & Legal Arguments", "Moralische & Rechtliche Argumente", "Argumenty Moralne i Prawne"),
                    content = LocalizedText(
                        tr = "Yönetimi ikna etmek için:\n\n- AHLAKİ: 'Hiçbir çalışan işini yapmak için hayatını riske atmamalıdır. Bu bizim insanlık görevimizdir.'\n- YASAL: Ulusal yasalara VEYA 'ILO C155 Sözleşmesi Madde 16'ya (Güvenli işyeri sağlama yükümlülüğü)' atıf yapın. 'Eğer yapmazsak müfettişler işi durdurabilir ve hapse girebiliriz' diyerek uyarın.",
                        en = "Persuade management:\n- MORAL: 'Duty of care. No one should die for a job.'\n- LEGAL: Quote National Laws OR 'ILO C155 Art 16'. Warn about enforcement notices and prosecution.",
                        de = "Moralisch (Fürsorgepflicht), Rechtlich (ILO C155, Strafverfolgung).",
                        pl = "Moralne (Obowiązek opieki), Prawne (ILO C155, Ściganie)."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("Finansal Gerekçe", "Financial Arguments", "Finanzielle Argumente", "Argumenty Finansowe"),
                    content = LocalizedText(
                        tr = "Para dilinden konuşun! Önlem almanın maliyeti ile kaza maliyetini kıyaslayın.\n\n- Önlem Maliyeti: 'Makine koruyucusu 500 TL'.\n- Kaza Maliyeti: 'Hastaneye yatış, tazminat, üretim durması, itibar kaybı = 50.000 TL'.\n\n'Bu önlem, olası bir kazadan çok daha ucuzdur' mesajını verin.",
                        en = "Talk money! Compare cost of control vs cost of accident.\n- Control: '$100 for a guard'.\n- Accident: 'Fines, compensation, downtime, reputation = $10,000'.\nArgue that safety is cheaper than accidents.",
                        de = "Kosten-Nutzen-Analyse. Unfallkosten sind höher als Präventionskosten.",
                        pl = "Analiza kosztów i korzyści. Koszty wypadku są wyższe niż prewencja."
                    ),
                    imageType = "warning"
                )
            )
        )
        neboshDao.insertTopic(Topic("12.3", "IG2_PRATIK", "3. Rapor ve Gerekçeler", "3. Report & Justification", "3. Bericht", "3. Raport", Gson().toJson(lesson12_3)))
    }
    // --- ELEMENT 11.2: KONTROL ÖNLEMLERİ (KORUMA SİSTEMLERİ) ---
    private suspend fun insertElement11Topic2() {

        val lesson11_2 = TopicContent(
            sections = listOf(
                // SAYFA 1: TEKNİK KORUMA SİSTEMLERİ (SİGORTA VE RCD)
                LessonSection(
                    title = LocalizedText("Koruma Cihazları", "Protective Devices", "Schutzeinrichtungen", "Urządzenia Ochronne"),
                    content = LocalizedText(
                        tr = "Elektrik tesisatını ve insanları koruyan temel cihazlar:\n\n1. SİGORTALAR (Fuses): Aşırı akım geçtiğinde eriyerek devreyi keser. Temel amacı kabloları ve cihazı korumaktır (İnsanı şoktan korumada yavaştır).\n2. KAÇAK AKIM RÖLESİ (RCD): Giren ve çıkan akımı karşılaştırır. Kaçak varsa (örn. birine elektrik çarpıyorsa) milisaniyeler içinde gücü keser. HAYAT KURTARAN asıl cihaz budur.\n3. TOPRAKLAMA (Earthing): Bir hata anında akımın direnci düşük olan toprak hattı üzerinden akmasını sağlayarak metal gövdenin elektriklenmesini önler.",
                        en = "Key protection systems:\n1. FUSES: Melt and break circuit during overcurrent. Protects equipment/cables.\n2. RCD (Residual Current Device): Compares incoming/outgoing current. Trips in milliseconds if leakage occurs. Primary life-saver.\n3. EARTHING: Provides a low-resistance path to earth for fault current, preventing metal casings from becoming live.",
                        de = "Sicherungen (Kabelschutz), RCD/FI-Schutzschalter (Personenschutz), Erdung.",
                        pl = "Bezpieczniki (ochrona sprzętu), RCD (ochrona ludzi), Uziemienie."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 2: İZOLASYON VE DÜŞÜK VOLTAJ
                LessonSection(
                    title = LocalizedText("İzolasyon ve Güvenli Voltaj", "Isolation & Safe Voltage", "Isolierung & Kleinspannung", "Izolacja i Niskie Napięcie"),
                    content = LocalizedText(
                        tr = "1. ÇİFT YALITIM: Cihazın hem canlı parçaları hem de dış gövdesi izole edilmiştir (Topraklama gerektirmez, 'Kare içinde kare' sembolü ile gösterilir).\n2. İNDİRGENMİŞ VOLTAJ (110V): Şantiyelerde kullanılır. Toprağa göre gerilim 55V'a düşürülerek ölümcül şok riski minimize edilir.\n3. AYIRMA/İZOLASYON: Bakım yapmadan önce enerjiyi kesmek ve kilitlemek (LOTO - Lock Out Tag Out).",
                        en = "1. DOUBLE INSULATION: Two layers of insulation, no earth wire needed (Square-in-square symbol).\n2. REDUCED VOLTAGE (110V): Used on construction sites. 55V to earth reduces fatal shock risk.\n3. ISOLATION: Cutting power and locking off (LOTO) before maintenance.",
                        de = "Doppelte Isolierung, 110V-Systeme (Baustellen), Freischalten (LOTO).",
                        pl = "Podwójna izolacja, Systemy 110V (budowy), Izolacja energii (LOTO)."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: GÜVENLİ ÇALIŞMA SİSTEMLERİ (SSW)
                LessonSection(
                    title = LocalizedText("Elektrikte Güvenli Çalışma", "Safe Systems of Work", "Sicheres Arbeitssystem", "Bezpieczny System Pracy"),
                    content = LocalizedText(
                        tr = "Elektrik işlerinde şu adımlar izlenmelidir:\n\n- YETKİNLİK: Sadece eğitimli ve ehliyetli elektrikçiler müdahale etmeli.\n- ÖLÜ (ENERJİSİZ) ÇALIŞMA: Prensip olarak her zaman enerji kesilmelidir.\n- CANLI ÇALIŞMA: Sadece başka alternatif yoksa, özel eğitim ve KKD (izole eldiven, halı) ile yapılmalıdır.\n- GÜVENLİ İZOLASYON: 1. Kapat, 2. Kilitle, 3. Test et (Enerji olmadığına emin ol).",
                        en = "Safe procedures:\n- COMPETENCE: Only trained electricians.\n- DEAD WORKING: Preferred method. Isolate first.\n- LIVE WORKING: Only as a last resort with special training and PPE.\n- SAFE ISOLATION: 1. Switch off, 2. Lock off, 3. Test before touch.",
                        de = "Kompetente Personen, Spannungsfrei arbeiten (bevorzugt), 5 Sicherheitsregeln.",
                        pl = "Kompetentne osoby, Praca bez napięcia (zalecana), 5 zasad bezpieczeństwa."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 4: ACİL DURUM VE İLK YARDIM
                LessonSection(
                    title = LocalizedText("Elektrik Kazasında Acil Durum", "Emergency Procedures", "Notfallprozeduren", "Procedury Ratunkowe"),
                    content = LocalizedText(
                        tr = "Birine elektrik çarptığını görürseniz:\n\n1. ASLA doğrudan dokunmayın (Siz de çarpılırsınız!).\n2. GÜCÜ KESİN: Şalteri indirin veya fişi çekin.\n3. GÜÇ KESİLEMİYORSUN: İzole bir nesneyle (kuru tahta sopa) kazazedeyi akımdan ayırın.\n4. İLK YARDIM: ABC (Hava yolu, Solunum, Dolaşım) kontrolü yapın, gerekirse Kalp Masajı (CPR) uygulayın.\n5. HASTANE: Tüm elektrik şokları (görünür yara olmasa bile) kalp ritmi kontrolü için tıbbi müdahale gerektirir.",
                        en = "In case of electric shock:\n1. DO NOT TOUCH: You will get shocked too.\n2. CUT POWER: Turn off switch/plug.\n3. IF POWER CAN'T BE CUT: Use non-conductive object (dry wood) to push person away.\n4. FIRST AID: Check ABC/CPR.\n5. HOSPITAL: All shocks need medical check for heart rhythm issues.",
                        de = "Nicht anfassen! Strom abschalten, Opfer mit isoliertem Gegenstand trennen, Erste Hilfe.",
                        pl = "Nie dotykaj! Wyłącz prąd, odsuń ofiarę izolatorem, Pierwsza Pomoc."
                    ),
                    imageType = "hazard"
                )
            )
        )
        // Konu ID'si 11.2
        neboshDao.insertTopic(Topic("11.2", "IG2", "Elektrik Kontrol Önlemleri", "Electrical Controls", "Elektrische Steuerungen", "Sterowanie Elektryczne", Gson().toJson(lesson11_2)))
    }
    // --- ELEMENT 11: ELEKTRİK ---
    private suspend fun insertElement11Topics() {

        // KONU 11.1: ELEKTRİK TEHLİKELERİ VE RİSKLERİ
        val lesson11_1 = TopicContent(
            sections = listOf(
                // SAYFA 1: ELEKTRİĞİN TEMEL TEHLİKELERİ
                LessonSection(
                    title = LocalizedText("Elektrik Tehlikeleri", "Electrical Hazards", "Elektrische Gefahren", "Zagrożenia Elektryczne"),
                    content = LocalizedText(
                        tr = "Elektrik enerjisi işyerlerinde üç ana yolla zarar verir:\n\n1. ELEKTRİK ŞOKU: Akımın vücut üzerinden geçmesi. Sinir sistemini ve kalbi durdurabilir.\n2. ELEKTRİK YANIKLARI: Akımın vücuda girdiği ve çıktığı noktalarda oluşan derin doku yanıkları.\n3. ARK VE PATLAMA: Havadan atlayan yüksek enerjili kıvılcımlar (Ark) yoğun ısı ve basınç dalgası yaratarak ciddi yanıklara ve göz hasarına neden olur.\n\n*Ayrıca elektrik, yangınların en büyük nedenlerinden biridir.*",
                        en = "Electricity harms in three main ways:\n1. ELECTRIC SHOCK: Current passing through the body. Can stop heart/breathing.\n2. BURNS: Deep tissue damage at entry and exit points.\n3. ARCING & EXPLOSION: High-energy sparks jumping through air, causing intense heat and pressure waves.",
                        de = "Elektrischer Schlag, Verbrennungen, Lichtbögen und Explosionen.",
                        pl = "Porażenie prądem, oparzenia, łuk elektryczny i wybuch."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: ŞOKUN ŞİDDETİNİ ETKİLEYEN FAKTÖRLER (SINAV SORUSU!)
                LessonSection(
                    title = LocalizedText("Şokun Şiddeti", "Severity of Shock", "Schwere des Schocks", "Stopień Porażenia"),
                    content = LocalizedText(
                        tr = "Elektrik çarpmasının ne kadar zarar vereceği şu faktörlere bağlıdır:\n\n- VOLTAJ: Gerilim ne kadar yüksekse risk o kadar artar.\n- AKIM (Amper): Asıl öldürücü olan akım miktarıdır.\n- VÜCUT DİRENCİ: Islak deri direnci düşürür ve şokun şiddetini artırır.\n- TEMAS SÜRESİ: Akıma ne kadar uzun süre maruz kalınırsa hasar o kadar büyük olur.\n- İZLENEN YOL: Akımın kalpten veya beyinden geçmesi (Örn: elden ele veya elden ayağa) ölümcül risk taşır.",
                        en = "Factors affecting severity:\n- VOLTAGE: Higher pressure means higher risk.\n- CURRENT (Amps): The flow that actually kills.\n- RESISTANCE: Wet skin lowers resistance, increasing shock.\n- DURATION: Longer contact = more damage.\n- PATHWAY: Passing through vital organs (e.g., hand-to-hand) is most dangerous.",
                        de = "Spannung, Stromstärke, Widerstand (nasse Haut), Dauer, Weg durch den Körper.",
                        pl = "Napięcie, Natężenie, Opór (mokra skóra), Czas, Droga przez ciało."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: TEHLİKELİ FAALİYETLER
                LessonSection(
                    title = LocalizedText("Tehlikeli Faaliyetler", "High-Risk Activities", "Gefährliche Tätigkeiten", "Niebezpieczne Działania"),
                    content = LocalizedText(
                        tr = "Elektrik kazalarının en sık yaşandığı durumlar:\n\n- Enerji Altında Çalışma: Devre kesilmeden yapılan tamiratlar.\n- Havai Hatlar: Vinç veya damperli kamyonların yüksek gerilim hatlarına teması.\n- Yeraltı Kabloları: Kazı çalışmaları sırasında kabloların parçalanması.\n- Hatalı Ekipman: İzolasyonu bozulmuş kablolar ve aşırı yüklenmiş prizler.\n- Islak Ortamlar: Elektrikli aletlerin ıslak zeminde veya yağmurda kullanımı.",
                        en = "Common high-risk scenarios:\n- Work on Live Systems: Repairs without isolation.\n- Overhead Lines: Cranes hitting high-voltage wires.\n- Underground Cables: Striking cables during excavation.\n- Faulty Equipment: Damaged insulation, overloaded sockets.\n- Wet Environments: Using electrical tools in rain/water.",
                        de = "Arbeiten an spannungsführenden Systemen, Freileitungen, Erdkabel, feuchte Umgebungen.",
                        pl = "Praca pod napięciem, linie napowietrzne, kable podziemne, wilgotne środowisko."
                    ),
                    imageType = "audit"
                )
            )
        )
        // Konu ID'si 11.1
        neboshDao.insertTopic(Topic("11.1", "IG2", "Elektrik Tehlikeleri", "Electrical Hazards", "Elektrische Gefahren", "Zagrożenia Elektryczne", Gson().toJson(lesson11_1)))
    }
    // --- ELEMENT 10.4: TAHLİYE VE GÜVENLİ KAÇIŞ ---
    private suspend fun insertElement10Topic4() {

        val lesson10_4 = TopicContent(
            sections = listOf(
                // SAYFA 1: KAÇIŞ YOLLARININ ÖZELLİKLERİ
                LessonSection(
                    title = LocalizedText("Güvenli Kaçış Yolları", "Means of Escape", "Fluchtwege", "Drogi Ewakuacyjne"),
                    content = LocalizedText(
                        tr = "Bir kaçış yolu, binanın herhangi bir yerinden 'Güvenli Yer'e (açık hava) giden yoldur.\n\nÖzellikleri:\n- Kaçış Mesafesi: Yangın çıkan yerden çıkış kapısına kadar olan mesafe sınırlı olmalı.\n- Yol Sayısı: Mümkünse en az iki farklı yöne giden çıkış olmalı (çıkmaz sokak riskini önlemek için).\n- Kapılar: Kaçış yönüne doğru açılmalı ve kilitli olmamalı (panik bar kullanılmalı).\n- Tabelalar: 'Fire Exit' tabelaları her yerden görülebilmeli ve ışıklı olmalıdır.\n- Aydınlatma: Elektrik kesilse bile yanan 'Acil Durum Aydınlatması' bulunmalıdır.",
                        en = "A route to a place of total safety:\n- Travel Distance: Must be limited.\n- Number of Exits: Ideally at least two directions of travel.\n- Doors: Open in direction of travel, no keys needed (Panic bars).\n- Signage: Illuminated and visible.\n- Emergency Lighting: Independent power source if mains fail.",
                        de = "Fluchtwege: Begrenzte Entfernung, Türen öffnen in Fluchtrichtung, Notbeleuchtung erforderlich.",
                        pl = "Drogi ewakuacyjne: Ograniczona odległość, drzwi otwierane na zewnątrz, oświetlenie awaryjne."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 2: TAHLİYE PROSEDÜRLERİ VE GÖREVLİLER
                LessonSection(
                    title = LocalizedText("Tahliye Planı ve Yangın Bekçileri", "Evacuation & Fire Wardens", "Evakuierung & Brandschutzhelfer", "Ewakuacja i Strażnicy Ogniowi"),
                    content = LocalizedText(
                        tr = "Başarılı bir tahliye için net bir plan gerekir:\n\n- Montaj Noktası (Assembly Point): Herkesin toplandığı güvenli açık alan.\n- Yangın Bekçileri (Fire Wardens): Belirli alanları kontrol eden, herkesin çıktığından emin olan ve yoklama yapan eğitimli kişiler.\n- Yoklama (Roll Call): Kimin içeride kaldığını itfaiyeye bildirmek için hayati önem taşır.\n- Asansörler: Yangın anında ASLA kullanılmamalıdır!",
                        en = "Plan requirements:\n- Assembly Point: Safe open space.\n- Fire Wardens: Trained staff to sweep areas and ensure everyone is out.\n- Roll Call: To identify missing persons for the Fire Service.\n- Lifts: NEVER use during a fire!",
                        de = "Sammelplatz, Brandschutzhelfer, Anwesenheitskontrolle. Aufzüge verboten.",
                        pl = "Miejsce zbiórki, strażnicy ogniowi, lista obecności. Windy zakazane."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 3: ENGELLİ VE HASTA TAHLİYESİ (PEEPs)
                LessonSection(
                    title = LocalizedText("Özel İhtiyaçlar (PEEPs)", "Special Needs (PEEPs)", "Besondere Bedürfnisse", "Potrzeby Specjalne"),
                    content = LocalizedText(
                        tr = "Engelli, yaşlı veya hamile çalışanlar için 'Kişisel Acil Tahliye Planı' (PEEP - Personal Emergency Evacuation Plan) hazırlanmalıdır.\n\n- Görme Engelliler: Sesli uyarılar veya rehber kişiler.\n- İşitme Engelliler: Görsel flaşörler veya titreşimli cihazlar.\n- Hareket Engelliler (Tekerlekli Sandalye): Güvenli bekleme alanları (Refuge areas) ve tahliye sandalyeleri (Evac-chairs).\n- Ziyaretçiler: Binayı bilmedikleri için personel tarafından yönlendirilmelidirler.",
                        en = "PEEP: Personal Emergency Evacuation Plan for disabled staff.\n- Visual Impairment: Audible warnings/buddies.\n- Hearing Impairment: Visual beacons/vibrating pagers.\n- Mobility Impairment: Refuge areas and Evac-chairs.\n- Visitors: Must be escorted or guided by staff.",
                        de = "PEEP (Persönliche Notfallpläne) für Behinderte. Evakuierungsstühle, Blitzleuchten.",
                        pl = "PEEP (Indywidualne plany ewakuacji). Krzesła ewakuacyjne, sygnalizacja optyczna."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 4: YANGIN TATBİKATLARI
                LessonSection(
                    title = LocalizedText("Yangın Tatbikatları", "Fire Drills", "Brandschutzübungen", "Próbna Ewakuacja"),
                    content = LocalizedText(
                        tr = "Planın çalışıp çalışmadığını test etmek için en az yılda bir kez tatbikat yapılmalıdır.\n\nAmaç:\n- Personelin kaçış yollarını öğrenmesi.\n- Yangın bekçilerinin performansının ölçülmesi.\n- Alarm sisteminin her yerde duyulduğunun teyidi.\n- Tahliye süresinin ölçülmesi.\n\nTatbikattan sonra bir rapor hazırlanmalı ve eksiklikler giderilmelidir.",
                        en = "Test the plan at least annually.\nPurpose:\n- Staff learn routes.\n- Test warden performance.\n- Check alarm audibility.\n- Measure evacuation time.\nA debrief/report should follow each drill.",
                        de = "Mindestens einmal jährlich üben. Wege lernen, Zeiten messen, Alarme prüfen.",
                        pl = "Przynajmniej raz w roku. Nauka dróg, pomiar czasu, test alarmów."
                    ),
                    imageType = "hazard"
                )
            )
        )
        // Konu ID'si 10.4
        neboshDao.insertTopic(Topic("10.4", "IG2", "Tahliye ve Güvenli Kaçış", "Evacuation & Safe Escape", "Evakuierung & Flucht", "Ewakuacja i Ucieczka", Gson().toJson(lesson10_4)))
    }
    // --- ELEMENT 10.3: YANGIN ALGILAMA VE YANGINLA MÜCADELE ---
    private suspend fun insertElement10Topic3() {

        val lesson10_3 = TopicContent(
            sections = listOf(
                // SAYFA 1: YANGIN ALGILAMA VE ALARMLAR
                LessonSection(
                    title = LocalizedText("Yangın Algılama Sistemleri", "Fire Detection & Alarm", "Brandmeldung", "Wykrywanie Pożaru"),
                    content = LocalizedText(
                        tr = "Yangını erken fark etmek hayat kurtarır. Sistemler ikiye ayrılır:\n\n1. MANUEL: Yangın ihbar butonları (Kır-Bas). İnsan müdahalesi gerekir.\n2. OTOMATİK: Dedektörler.\n   - Isı Dedektörleri: Mutfak gibi dumanlı/tozlu yerler için uygundur.\n   - Duman Dedektörleri: Ofis ve koridorlar için idealdir (Daha hızlıdır).\n   - Alev Dedektörleri: Çok hızlı yanan sıvıların olduğu geniş alanlar.\n\nAlarm: Görsel (flaşör) ve işitsel (siren) olmalı, her yerden duyulmalıdır.",
                        en = "Early detection saves lives:\n1. MANUAL: Break-glass call points.\n2. AUTOMATIC: Detectors.\n   - Heat Detectors: For smoky/dusty areas (kitchens).\n   - Smoke Detectors: For offices/corridors (faster).\n   - Flame Detectors: For large areas with flammable liquids.\nAlarm: Must be both visual and audible.",
                        de = "Manuelle Melder, automatische Melder (Rauch, Hitze, Flamme). Akustische und optische Alarme.",
                        pl = "Ręczne ostrzegacze, czujki automatyczne (dymu, ciepła, płomienia). Alarmy dźwiękowe i świetlne."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 2: SÖNDÜRÜCÜ TÜRLERİ (RENK KODLARI)
                LessonSection(
                    title = LocalizedText("Yangın Söndürücüler", "Fire Extinguishers", "Feuerlöscher", "Gaśnice"),
                    content = LocalizedText(
                        tr = "Her söndürücü her yangında kullanılmaz! Etiket renkleri şöyledir:\n\n- SU (Kırmızı): Sadece Sınıf A (Kağıt, odun). Elektrikte ASLA kullanma!\n- KÖPÜK (Krem): Sınıf A ve B (Sıvı yangınları).\n- KURU TOZ (Mavi): A, B, C sınıfları için (Çok amaçlı). Görüşü kısıtlayabilir.\n- CO2 (Siyah): Elektrik yangınları ve Sınıf B. Kapalı alanda boğulma riski yaratır.\n- ISLAK KİMYASAL (Sarı): Sınıf F (Mutfak/Yağ yangınları).",
                        en = "Color codes & Uses:\n- WATER (Red): Class A only. NEVER on electrics!\n- FOAM (Cream): Class A & B.\n- DRY POWDER (Blue): Multi-purpose (A, B, C). Can obscure vision.\n- CO2 (Black): Electrical & Class B. Risk of asphyxiation in small rooms.\n- WET CHEMICAL (Yellow): Class F (Kitchen oils).",
                        de = "Wasser (Rot), Schaum (Creme), Pulver (Blau), CO2 (Schwarz), Fettbrandlöscher (Gelb).",
                        pl = "Woda (Czerwona), Pianowa (Kremowa), Proszkowa (Niebieska), CO2 (Czarna), Gastronomiczna (Żółta)."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: SÖNDÜRME PRENSİPLERİ
                LessonSection(
                    title = LocalizedText("Yangın Nasıl Söndürülür?", "Extinguishing Principles", "Löschprinzipien", "Zasady Gaszenia"),
                    content = LocalizedText(
                        tr = "Yangın üçgenindeki bir unsuru yok ederek söndürme yapılır:\n\n1. SOĞUTMA (Cooling): Isıyı düşürmek (Su kullanarak).\n2. BOĞMA (Smothering): Oksijeni kesmek (Köpük, CO2 veya yangın battaniyesi).\n3. YAKITI KESME (Starvation): Yanıcı madde akışını durdurmak (Gaz vanasını kapatmak).\n4. ZİNCİRLEME REAKSİYONU DURDURMA: Kuru toz kullanarak kimyasal tepkimeyi kesmek.",
                        en = "Based on removing one element of the fire triangle:\n1. COOLING: Removing heat (Water).\n2. SMOTHERING: Removing oxygen (Foam, CO2, fire blanket).\n3. STARVATION: Removing fuel (Turning off gas valve).\n4. CHEMICAL INTERRUPTION: Using powder to break the reaction.",
                        de = "Kühlen (Wasser), Ersticken (CO2/Schaum), Aushungern (Brennstoff entfernen).",
                        pl = "Chłodzenie (Woda), Duszenie (CO2/Piana), Głodzenie (Odcięcie paliwa)."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 4: SABİT SİSTEMLER
                LessonSection(
                    title = LocalizedText("Sabit Söndürme Sistemleri", "Fixed Systems", "Stationäre Löschanlagen", "Stałe Urządzenia Gaśnicze"),
                    content = LocalizedText(
                        tr = "Binalarda otomatik müdahale sağlayan sistemler:\n\n- Sprinkler Sistemleri: Isıyı hissedince su püskürten başlıklar.\n- Gazlı Söndürme: Sunucu (server) odaları gibi suyun zarar vereceği yerlerde kullanılır (Örn. Inergen veya CO2).\n- Yangın Hortum Makaraları: Su kaynağına bağlı sabit makaralar.\n\nÖnemli: Sabit sistemlerin bakımı ve periyodik kontrolleri aksatılmamalıdır.",
                        en = "Automatic systems:\n- Sprinklers: Water spray activated by heat.\n- Gaseous Systems: For server rooms (Inergen/CO2).\n- Fire Hose Reels.\nNote: Regular maintenance of these systems is vital.",
                        de = "Sprinkleranlagen, Gaslöschanlagen (Serverräume), Wandhydranten.",
                        pl = "Tryskacze, Systemy gazowe (serwerownie), Hydranty wewnętrzne."
                    ),
                    imageType = "hazard"
                )
            )
        )
        // Konu ID'si 10.3
        neboshDao.insertTopic(Topic("10.3", "IG2", "Yangınla Mücadele", "Firefighting", "Brandbekämpfung", "Zwalczanie Pożarów", Gson().toJson(lesson10_3)))
    }
    // --- ELEMENT 10.2: YANGIN VE YANGININ YAYILMASINI ÖNLEME ---
    private suspend fun insertElement10Topic2() {

        val lesson10_2 = TopicContent(
            sections = listOf(
                // SAYFA 1: TUTUŞMA KAYNAKLARININ KONTROLÜ (ISI)
                LessonSection(
                    title = LocalizedText("Isı Kaynaklarının Kontrolü", "Control of Ignition Sources", "Kontrolle von Zündquellen", "Kontrola Źródeł Zapłonu"),
                    content = LocalizedText(
                        tr = "Yangını önlemenin ilk yolu ısı kaynaklarını yönetmektir:\n\n- ELEKTRİK: Ekipmanların aşırı ısınmasını önleyin, PAT testlerini yapın.\n- SİGARA: Sadece belirlenmiş güvenli alanlarda içilmesine izin verin.\n- ATEŞLİ İŞLER: Kaynak, kesme gibi işler için 'Sıcak Çalışma İzin Sistemi' (Hot Work Permit) uygulayın. İş bittikten sonra en az 30-60 dk yangın gözlemi yapın.\n- PORTATİF ISITICILAR: Yanıcı maddelerden uzak tutun, devrilme emniyeti olduğundan emin olun.",
                        en = "Control heat to prevent ignition:\n- ELECTRICAL: Prevent overheating, perform PAT tests.\n- SMOKING: Designated safe areas only.\n- HOT WORK: Use 'Hot Work Permit' for welding/cutting. Maintain fire watch for 30-60 mins after.\n- HEATERS: Keep away from flammables, ensure tilt-switches work.",
                        de = "Zündquellen kontrollieren: Elektrik, Rauchen, Heißarbeiten (Genehmigung), Heizgeräte.",
                        pl = "Kontrola źródeł zapłonu: Elektryka, Palenie, Prace gorące (Pozwolenie), Grzejniki."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 2: YAKIT KAYNAKLARININ KONTROLÜ (YANICI MADDELER)
                LessonSection(
                    title = LocalizedText("Yanıcı Maddelerin Depolanması", "Storage of Flammables", "Lagerung brennbarer Stoffe", "Magazynowanie Materiałów Palnych"),
                    content = LocalizedText(
                        tr = "Yanıcı sıvı ve gazlar büyük risk taşır:\n\n1. MİKTAR: Çalışma alanında sadece o gün yetecek kadar (minimum) madde bulundurun.\n2. DEPOLAMA: Ateşe dayanıklı dolaplarda (kabinetlerde) saklayın. Dökülmelere karşı toplama tepsisi (bunding) kullanın.\n3. AYIRMA: Yanıcı maddeleri oksitleyici maddelerden veya ısı kaynaklarından uzak tutun.\n4. TEMİZLİK: Boş kutuları ve atık kağıtları biriktirmeyin, düzenli olarak dışarı atın.",
                        en = "Managing fuel sources:\n1. QUANTITY: Keep minimum amounts in work areas.\n2. STORAGE: Fire-resistant cabinets. Use bunds for leaks.\n3. SEPARATION: Keep away from oxidisers or heat sources.\n4. HOUSEKEEPING: Remove waste paper and empty containers regularly.",
                        de = "Lagerung: Mengen minimieren, Brandschutzschränke, Trennung von Oxidationsmitteln.",
                        pl = "Magazynowanie: Minimalizacja ilości, szafy ognioodporne, oddzielenie od utleniaczy."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 3: YAPISAL YANGIN KORUMASI (BÖLÜMLEME)
                LessonSection(
                    title = LocalizedText("Bölümleme ve Yangın Kapıları", "Compartmentation & Fire Doors", "Brandabschnitte", "Oddzielenia Przeciwpożarowe"),
                    content = LocalizedText(
                        tr = "Bina, yangını ve dumanı belirli bir alanda hapsetmek için bölümlere ayrılmalıdır (Compartmentation).\n\n- Yangın Duvarları/Zeminleri: Yangına en az 30-60 dk dayanıklı malzemeden yapılır.\n- YANGIN KAPILARI: Kendiliğinden kapanan (self-closing) mekanizmalı olmalıdır. ASLA takozla açık bırakılmamalıdır!\n- Menfezler: Havalandırma kanallarına yangın damperleri (fire dampers) takılmalıdır; ısıyı hissedince yolu otomatik kapatır.",
                        en = "Structural protection to contain fire/smoke:\n- FIRE COMPARTMENTS: Walls/floors with 30-60 min fire resistance.\n- FIRE DOORS: Must be self-closing. NEVER wedge them open!\n- DAMPERS: Fire dampers in vents close automatically when heat is detected.",
                        de = "Brandabschnitte (Wände/Böden), Brandschutztüren (selbstschließend, nicht verkeilen!), Brandschutzklappen.",
                        pl = "Oddzielenia (ściany/stropy), Drzwi przeciwpożarowe (samozamykające), Klapy przeciwpożarowe."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 4: DUMANIN KONTROLÜ
                LessonSection(
                    title = LocalizedText("Dumanın Kontrolü", "Control of Smoke", "Rauchkontrolle", "Kontrola Dymu"),
                    content = LocalizedText(
                        tr = "Yangınlarda ölümlerin çoğu alevden değil, duman solumadan kaynaklanır. Dumanı yönetmek için:\n\n- Duman Perdeleri: Tavandan sarkan dumanı yönlendiren engeller.\n- Çatı Menfezleri: Dumanın yükselip binadan tahliye edilmesini sağlar.\n- Basınçlandırma: Merdiven boşluklarına temiz hava basarak dumanın kaçış yollarına girmesini engeller.",
                        en = "Most deaths are from smoke, not flames. To manage smoke:\n- Smoke Curtains: Direct the smoke.\n- Roof Vents: Allow smoke to escape the building.\n- Pressurisation: Fresh air in stairwells to keep smoke out of escape routes.",
                        de = "Rauchvorhänge, Dachluken, Treppenhaus-Druckbelüftung.",
                        pl = "Kurtyny dymowe, klapy dymowe, nadciśnienie w klatkach schodowych."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 10.2
        neboshDao.insertTopic(Topic("10.2", "IG2", "Yangın Önleme Yöntemleri", "Fire Prevention", "Brandprävention", "Zapobieganie Pożarom", Gson().toJson(lesson10_2)))
    }
    // --- ELEMENT 10: YANGIN ---
    private suspend fun insertElement10Topics() {
        // KONU 10.1: YANGIN PRENSİPLERİ, SINIFLANDIRMA VE YAYILMA
        val lesson10_1 = TopicContent(
            sections = listOf(
                // SAYFA 1: YANGIN ÜÇGENİ
                LessonSection(
                    title = LocalizedText("Yangın Üçgeni", "The Fire Triangle", "Das Branddreieck", "Trójkąt Ogniowy"),
                    content = LocalizedText(
                        tr = "Bir yangının başlaması ve devam etmesi için 3 unsurun aynı anda bulunması gerekir:\n\n1. YAKIT (Fuel): Yanıcı madde (kağıt, odun, gaz vb.).\n2. ISI (Heat): Tutuşma sıcaklığı (kıvılcım, açık alev, sürtünme).\n3. OKSİJEN: Havada bulunan oksijen.\n\nYangını söndürmek için bu unsurlardan en az birini ortamdan uzaklaştırmanız gerekir.",
                        en = "Fire requires 3 elements to start and sustain:\n1. FUEL: Flammable material.\n2. HEAT: Ignition source.\n3. OXYGEN: From the air.\nRemoving any one of these will extinguish the fire.",
                        de = "Brennstoff, Wärme, Sauerstoff. Entferne eins, und das Feuer erlischt.",
                        pl = "Paliwo, Ciepło, Tlen. Usuń jeden element, a ogień zgaśnie."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: YANGINLARIN SINIFLANDIRILMASI (ÇOK ÖNEMLİ!)
                LessonSection(
                    title = LocalizedText("Yangın Sınıfları", "Classification of Fires", "Brandklassen", "Klasyfikacja Pożarów"),
                    content = LocalizedText(
                        tr = "Yangınlar yanan maddenin türüne göre sınıflara ayrılır:\n\n- SINIF A: Katı madde (Odun, kağıt, kumaş).\n- SINIF B: Yanıcı sıvılar (Benzin, yağ, boya).\n- SINIF C: Yanıcı gazlar (LPG, Doğalgaz, Metan).\n- SINIF D: Metal yangınları (Magnezyum, Alüminyum).\n- SINIF F (veya K): Pişirme yağları (Mutfak yangınları).\n\n*Not: Elektrik bir sınıf değildir, ancak elektrikli ekipman yangınlarında iletken olmayan söndürücü (CO2) seçilmelidir.*",
                        en = "Classified by the fuel involved:\n- CLASS A: Solids (Wood, paper).\n- CLASS B: Liquids (Petrol, oil).\n- CLASS C: Gases (LPG, Methane).\n- CLASS D: Metals (Magnesium).\n- CLASS F: Cooking oils/fats.\n*Electrical is not a class, but influences extinguisher choice.*",
                        de = "A: Feststoffe, B: Flüssigkeiten, C: Gase, D: Metalle, F: Speiseöle.",
                        pl = "A: Ciała stałe, B: Ciecze, C: Gazy, D: Metale, F: Oleje jadalne."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: ISININ YAYILMA YOLLARI
                LessonSection(
                    title = LocalizedText("Yangının Yayılması", "Spread of Fire", "Brandbeurteilung", "Rozprzestrzenianie się ognia"),
                    content = LocalizedText(
                        tr = "Yangın 4 ana yolla yayılır:\n\n1. İLETİM (Conduction): Isının katı maddeler (örn. metal borular) üzerinden geçmesi.\n2. TAŞINIM (Convection): Sıcak hava ve dumanın yükselerek üst katlara yayılması (En yaygın yayılma yolu).\n3. IŞINIM (Radiation): Isının kızılötesi dalgalarla havadan boşluğa yayılması (Güneş ısısı gibi).\n4. DOĞRUDAN YANMA: Alevin başka bir maddeye doğrudan temas etmesi.",
                        en = "4 Main methods:\n1. CONDUCTION: Heat moving through solids (e.g. metal pipes).\n2. CONVECTION: Hot air/smoke rising (Main cause of death/spread).\n3. RADIATION: Heat moving through air via waves (like sun rays).\n4. DIRECT BURNING: Flame touching another object.",
                        de = "Wärmeleitung, Konvektion (Heißluft), Strahlung, Direktes Brennen.",
                        pl = "Przewodzenie, Konwekcja, Promieniowanie, Bezpośrednie palenie."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 4: İŞYERİNDEKİ YAYGIN NEDENLER
                LessonSection(
                    title = LocalizedText("Yangın Nedenleri", "Common Causes", "Häufige Ursachen", "Częste Przyczyny"),
                    content = LocalizedText(
                        tr = "İşyerlerinde yangınlar genellikle şunlardan çıkar:\n\n- Elektrik Arızaları: Aşırı yüklenme veya kısa devre.\n- Ateşli İşler: Kaynak ve kesme işlemleri.\n- Sigara: Dikkatsizce atılan izmaritler.\n- Kundaklama: Kasıtlı yakma.\n- Yemek Pişirme: Mutfaklardaki yağ yangınları.\n- Isıtma Cihazları: Portatif ısıtıcıların yanıcı maddelere yakın olması.",
                        en = "Typical workplace causes:\n- Electrical Faults: Overloading.\n- Hot Work: Welding/cutting.\n- Smoking: Careless disposal.\n- Arson: Deliberate ignition.\n- Cooking: Kitchen fats.\n- Heating Appliances: Portable heaters.",
                        de = "Elektrik, Schweißen, Rauchen, Brandstiftung, Kochen, Heizgeräte.",
                        pl = "Elektryka, Spawanie, Palenie, Podpalenie, Gotowanie, Grzejniki."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 10.1
        neboshDao.insertTopic(Topic("10.1", "IG2", "Yangın Prensipleri", "Fire Principles", "Brandprinzipien", "Zasady Pożarowe", Gson().toJson(lesson10_1)))
    }
    // --- ELEMENT 9.4: MAKİNE KORUMA YÖNTEMLERİ ---
    private suspend fun insertElement9Topic4() {

        val lesson9_4 = TopicContent(
            sections = listOf(
                // SAYFA 1: KORUYUCU TÜRLERİ (SABİT VE AYARLANABİLİR)
                LessonSection(
                    title = LocalizedText("Sabit ve Ayarlanabilir Koruyucular", "Fixed & Adjustable Guards", "Feste & Verstellbare Schutzeinrichtungen", "Osłony Stałe i Nastawne"),
                    content = LocalizedText(
                        tr = "1. SABİT KORUYUCU: Hareketli parçalara erişimi tamamen kapatan fiziksel engeldir. Sadece aletle (anahtar, tornavida) açılabilir. En güvenli yöntemdir.\n2. AYARLANABİLİR KORUYUCU: Malzeme boyutuna göre operatör tarafından elle ayarlanır (Örn: Sütunlu matkap ucu koruması).\n3. KENDİNDEN AYARLI: İş parçası geçtikçe kendiliğinden açılır ve iş bitince kapanır (Örn: Daire testere bıçak siperi).",
                        en = "1. FIXED GUARD: Physical barrier with no moving parts. Can only be removed with a tool (spanner). Best choice.\n2. ADJUSTABLE: Set by operator to suit material (e.g., drill guard).\n3. SELF-ADJUSTING: Opens as material passes and closes automatically (e.g., circular saw guard).",
                        de = "Feste Schutzhaube (nur mit Werkzeug lösbar), Verstellbar, Selbsttätig verstellbar.",
                        pl = "Osłona stała (tylko narzędziem), Nastawna, Samonastawna."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 2: KİLİTLİ KORUYUCULAR (INTERLOCKED)
                LessonSection(
                    title = LocalizedText("Kilitli Koruyucular", "Interlocked Guards", "Verriegelte Schutzeinrichtungen", "Osłony Blokujące"),
                    content = LocalizedText(
                        tr = "Kilitli (Interlocked) koruyucu, makinenin çalışmasını kapının durumuna bağlar.\n\nNasıl Çalışır?\n- Koruyucu (kapak) açıkken makine ASLA çalışmaz.\n- Makine çalışırken koruyucu açılırsa makine HEMEN durur.\n\nKullanım Alanı: Malzeme yüklemek için sık sık açılması gereken kapaklar (Örn: Fotokopi makinesi kapakları, mikrodalga fırınlar, büyük pres kabinleri).",
                        en = "Interlocked guards link machine power to the guard position.\nHow it works:\n- Machine won't start if guard is open.\n- Opening the guard while running STOPS the machine immediately.\nUse: Where frequent access is needed (e.g., photocopiers, CNC machines).",
                        de = "Verriegelung: Maschine läuft nur, wenn Schutz geschlossen ist. Stoppt sofort beim Öffnen.",
                        pl = "Blokada: Maszyna działa tylko przy zamkniętej osłonie. Stop przy otwarciu."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: DURDURMA CİHAZLARI (TRIP DEVICES)
                LessonSection(
                    title = LocalizedText("Durdurma Cihazları ve Işık Bariyerleri", "Trip Devices & Light Curtains", "Schutzeinrichtungen mit Annäherungsreaktion", "Urządzenia Odłączające"),
                    content = LocalizedText(
                        tr = "Fiziksel bir engel yerine tehlikeyi algılayıp sistemi durduran cihazlardır:\n\n- IŞIK BARİYERLERİ (Light Curtains): Görünmez kızılötesi ışınlar kesilirse makine durur.\n- BASINÇ HASSAS PASPASLAR: Üzerine basıldığında makineyi durdurur.\n- İKİ EL KUMANDASI: Operatörün her iki elini de butonlarda tutmasını zorunlu kılar (Eller tehlike bölgesine giremez).\n- ACİL DURDURMA: Kırmızı mantar buton. Kazayı önlemez, sonucun şiddetini azaltır.",
                        en = "Non-physical barriers that stop the machine:\n- LIGHT CURTAINS: Stops if infrared beams are broken.\n- PRESSURE MATS: Stops if stepped on.\n- TWO-HAND CONTROLS: Keeps both hands away from danger zone.\n- EMERGENCY STOP: Red mushroom button. Reduces severity, doesn't prevent initial accident.",
                        de = "Lichtvorhänge, Schaltmatten, Zweihandschaltung, Not-Halt-Taster.",
                        pl = "Kurtyny świetlne, maty naciskowe, sterowanie oburęczne, stop awaryjny."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 4: KORUYUCU GEREKLİLİKLERİ
                LessonSection(
                    title = LocalizedText("İyi Bir Koruyucunun Özellikleri", "Requirements for Guards", "Anforderungen an Schutzeinrichtungen", "Wymagania dla Osłon"),
                    content = LocalizedText(
                        tr = "Bir koruyucu şunları sağlamalıdır:\n\n- Sağlam ve dayanıklı olmalı.\n- Yeni bir tehlike yaratmamalı (keskin kenar vb.).\n- Devre dışı bırakılması (baypas edilmesi) zor olmalı.\n- Görüşü engellememeli.\n- Bakım için kolaylık sağlamalı.\n- Tehlike bölgesine erişimi İMKANSIZ kılmalı.",
                        en = "A good guard must be:\n- Robust and strong.\n- Create no new hazards.\n- Hard to bypass/cheat.\n- Not obstruct vision.\n- Allow for maintenance.\n- Make access to danger zone impossible.",
                        de = "Robust, keine neuen Gefahren, manipulationssicher, wartungsfreundlich.",
                        pl = "Solidna, brak nowych zagrożeń, odporna na manipulacje, ułatwiająca konserwację."
                    ),
                    imageType = "audit"
                )
            )
        )
        // Konu ID'si 9.4
        neboshDao.insertTopic(Topic("9.4", "IG2", "Makine Koruma Yöntemleri", "Machinery Safeguarding", "Maschinenschutz", "Zabezpieczenia Maszyn", Gson().toJson(lesson9_4)))
    }
    // --- ELEMENT 9.3: MAKİNE TEHLİKELERİ (MEKANİK VE MEKANİK OLMAYAN) ---
    private suspend fun insertElement9Topic3() {

        val lesson9_3 = TopicContent(
            sections = listOf(
                // SAYFA 1: MEKANİK TEHLİKELER (ANA TÜRLER)
                LessonSection(
                    title = LocalizedText("Mekanik Tehlikeler", "Mechanical Hazards", "Mechanische Gefährdungen", "Zagrożenia Mechaniczne"),
                    content = LocalizedText(
                        tr = "Makinelerin hareketli parçalarından kaynaklanan tehlikelerdir:\n\n1. DOLANMA (Entanglement): Dönen bir parçanın kıyafeti, saçı veya eldiveni sarması (Örn: Matkap ucu).\n2. KESME (Cutting): Keskin bir kenarın teması (Örn: Testere bıçağı).\n3. EZİLME (Crushing): İki hareketli parça veya bir hareketli bir sabit parça arasında kalma.\n4. ÇEKME/HAPSETME (Drawing-in/Entrapment): İki dönen parçanın (dişli, silindir) içine çekmesi.\n5. DELME/SAPLANMA: İğne veya fırlayan bir parça.",
                        en = "Hazards from moving parts:\n1. ENTANGLEMENT: Clothing/hair caught in rotating parts (e.g. drill).\n2. CUTTING: Contact with sharp edges (e.g. saw).\n3. CRUSHING: Being caught between moving/fixed parts.\n4. DRAWING-IN/ENTRAPMENT: Being drawn into in-running nips (e.g. rollers/gears).\n5. PUNCTURE: Injection or flying objects.",
                        de = "Erfassen (Aufwickeln), Schneiden, Quetschen, Einziehen, Durchstechen.",
                        pl = "Pochwycenie (nawinięcie), Przecięcie, Zgniecenie, Wciągnięcie, Przebicie."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: MEKANİK OLMAYAN TEHLİKELER
                LessonSection(
                    title = LocalizedText("Mekanik Olmayan Tehlikeler", "Non-Mechanical Hazards", "Nicht-mechanische Gefährdungen", "Zagrożenia Niemechaniczne"),
                    content = LocalizedText(
                        tr = "Makineler sadece hareketli parçalarıyla değil, diğer enerjileriyle de zarar verir:\n\n- ELEKTRİK: Şok veya yanık.\n- GÜRÜLTÜ: İşitme kaybı.\n- TİTREŞİM: Beyaz parmak hastalığı.\n- TEHLİKELİ MADDELER: Egzoz gazları, tozlar veya soğutma sıvıları.\n- SICAKLIK: Sıcak yüzeylerle temas (yanık).\n- RADYASYON: Lazerler veya iyonlaştırıcı radyasyon.\n- ERGONOMİ: Kötü duruş veya tekrarlayan hareketler.",
                        en = "Other risks from machinery:\n- ELECTRICITY: Shock/burn.\n- NOISE: Hearing loss.\n- VIBRATION: Hand-arm vibration.\n- HAZARDOUS SUBSTANCES: Fumes, dust, coolants.\n- TEMPERATURE: Burns from hot surfaces.\n- RADIATION: Lasers.\n- ERGONOMICS: Poor posture.",
                        de = "Strom, Lärm, Vibration, Gefahrstoffe, Temperatur, Strahlung, Ergonomie.",
                        pl = "Prąd, Hałas, Wibracje, Substancje niebezpieczne, Temperatura, Promieniowanie, Ergonomia."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: RİSK DEĞERLENDİRMESİ
                LessonSection(
                    title = LocalizedText("Makine Risk Değerlendirmesi", "Risk Assessment", "Risikobeurteilung", "Ocena Ryzyka"),
                    content = LocalizedText(
                        tr = "Bir makineyi değerlendirirken şunlara bakılmalıdır:\n\n- Kurulum: Makine sabitlenmiş mi? Etrafında yeterli alan var mı?\n- Operasyon: Normal kullanım sırasında hangi tehlikeler var?\n- Bakım: Bakım yapılırken makine nasıl durdurulacak (LOTO)?\n- Kimler Risk Altında: Operatör mü, yoldan geçenler mi yoksa temizlikçiler mi?\n- Olağan Dışı Durumlar: Tıkanıklık açma veya ayar yapma sırasında oluşan riskler.",
                        en = "Consider:\n- Installation: Is it fixed? Space?\n- Operation: Normal use hazards.\n- Maintenance: How to stop safely (LOTO)?\n- Who is at risk: Operator, bystanders, cleaners?\n- Abnormal conditions: Clearing blockages or adjustments.",
                        de = "Installation, Betrieb, Wartung, gefährdete Personen, Störungsbeseitigung.",
                        pl = "Instalacja, Obsługa, Konserwacja, Osoby zagrożone, Usuwanie zatorów."
                    ),
                    imageType = "audit"
                )
            )
        )
        // Konu ID'si 9.3
        neboshDao.insertTopic(Topic("9.3", "IG2", "Makine Tehlikeleri", "Machinery Hazards", "Maschinengefahren", "Zagrożenia Maszynowe", Gson().toJson(lesson9_3)))
    }
    // --- ELEMENT 9.2: EL ALETLERİ VE TAŞINABİLİR ELEKTRİKLİ ALETLER ---
    private suspend fun insertElement9Topic2() {

        val lesson9_2 = TopicContent(
            sections = listOf(
                // SAYFA 1: EL ALETLERİ (GÜÇSÜZ)
                LessonSection(
                    title = LocalizedText("El Aletleri Tehlikeleri", "Hand Tool Hazards", "Gefahren durch Handwerkzeuge", "Zagrożenia Narzędzi Ręcznych"),
                    content = LocalizedText(
                        tr = "Çekiç, tornavida, anahtar gibi aletler 'basit' görünse de ciddi kazalara yol açabilir. \n\nTehlikeler:\n- Yanlış Kullanım: Tornavidayı keski gibi kullanmak.\n- Bakımsızlık: Başı gevşemiş çekiç, ağzı bozulmuş anahtar (kayma yapar).\n- Depolama: Keskin aletlerin cepte taşınması veya masada açık bırakılması.\n\nKontroller: İşe uygun alet seçimi, düzenli kontrol ve kılıf kullanımı.",
                        en = "Simple tools like hammers and screwdrivers can cause serious accidents.\nHazards:\n- Misuse: Using a screwdriver as a chisel.\n- Poor Maintenance: Mushroomed heads on chisels, loose hammer heads.\n- Storage: Carrying sharp tools in pockets.\nControls: Correct tool for the job, routine inspection.",
                        de = "Gefahren: Fehlgebrauch, mangelnde Wartung, falsche Lagerung. Nur geeignete Werkzeuge verwenden.",
                        pl = "Zagrożenia: Niewłaściwe użycie, brak konserwacji, złe przechowywanie. Używaj właściwych narzędzi."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: TAŞINABİLİR ELEKTRİKLİ ALETLER
                LessonSection(
                    title = LocalizedText("Taşınabilir Elektrikli Aletler", "Portable Power Tools", "Tragbare Elektrowerkzeuge", "Elektronarzędzia Przenośne"),
                    content = LocalizedText(
                        tr = "Matkaplar, dairesel testereler ve taşlama makineleri daha yüksek risk taşır:\n\nTehlikeler:\n- Elektrik Şoku: Hasarlı kablolar veya ıslak ortam.\n- Hareketli Parçalar: Kesilme veya uzuv kaybı.\n- Gürültü ve Titreşim: El-kol titreşimi (HAVS).\n- Toz: Silika veya odun tozu solunması.\n- Göz Yaralanması: Fırlayan çapaklar veya kıvılcımlar.",
                        en = "Drills, saws, and grinders carry higher risks:\nHazards:\n- Electric Shock: Damaged cables.\n- Moving Parts: Cuts and entanglements.\n- Noise & Vibration: HAVS.\n- Dust: Inhalation of silica/wood dust.\n- Eye Injury: Flying sparks/debris.",
                        de = "Risiken: Stromschlag, bewegliche Teile, Lärm, Vibration, Staub, Augenverletzungen.",
                        pl = "Ryzyko: Porażenie prądem, ruchome części, hałas, wibracje, pył, urazy oczu."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: ELEKTRİK GÜVENLİĞİ VE KONTROL
                LessonSection(
                    title = LocalizedText("Elektrik Güvenliği Kontrolleri", "Electrical Safety Controls", "Elektrische Sicherheit", "Bezpieczeństwo Elektryczne"),
                    content = LocalizedText(
                        tr = "Elektrikli aletlerde risk yönetimi:\n\n1. Düşük Voltaj: İnşaat sahalarında 110V (Sarı trafo) kullanımı (Ölüm riskini azaltır).\n2. Kaçak Akım Rölesi (RCD): Elektrik kaçağı anında gücü keser.\n3. Kablo Yönetimi: Kabloların takılma tehlikesi yaratmaması ve ezilmemesi sağlanmalıdır.\n4. Görsel Kontrol: Kullanıcı her kullanımdan önce kabloyu ve fişi kontrol etmelidir.\n5. PAT Testi: Taşınabilir aletlerin periyodik olarak elektrik testinden geçirilmesi.",
                        en = "Management:\n1. Reduced Voltage: Using 110V on sites.\n2. RCD: Residual Current Device cuts power fast.\n3. Cable Management: Avoid trip hazards.\n4. User Check: Visual check before each use.\n5. PAT Testing: Periodic formal electrical testing.",
                        de = "110V-Systeme, RCD (FI-Schalter), Kabelführung, Sichtprüfung, PAT-Prüfung.",
                        pl = "Systemy 110V, RCD, prowadzenie kabli, kontrola wzrokowa, testy PAT."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 9.2
        neboshDao.insertTopic(Topic("9.2", "IG2", "El Aletleri ve Taşınabilir Aletler", "Hand and Portable Power Tools", "Handwerkzeuge", "Narzędzia Ręczne i Przenośne", Gson().toJson(lesson9_2)))
    }
    // --- ELEMENT 9: İŞ EKİPMANLARI ---
    private suspend fun insertElement9Topics() {

        // KONU 9.1: GENEL ŞARTLAR (BAKIM, EĞİTİM, UYGUNLUK)
        val lesson9_1 = TopicContent(
            sections = listOf(
                // SAYFA 1: İŞ EKİPMANI NEDİR?
                LessonSection(
                    title = LocalizedText("İş Ekipmanı Nedir?", "What is Work Equipment?", "Was ist ein Arbeitsmittel?", "Co to jest sprzęt roboczy?"),
                    content = LocalizedText(
                        tr = "İş yerinde kullanılan HER TÜRLÜ alet ve makinedir. Kapsam çok geniştir:\n\n- Basit El Aletleri: Çekiç, tornavida, bıçak.\n- Makineler: Fotokopi makinesi, matkap, pres makinesi.\n- Mobil Ekipmanlar: Forklift, kamyon.\n- Tesisat: Asansörler, ısıtma sistemleri.\n\nİşveren, bu ekipmanların çalışanlara zarar vermemesini sağlamalıdır.",
                        en = "Includes EVERYTHING used at work:\n- Hand Tools: Hammers, knives.\n- Machinery: Photocopiers, drills, presses.\n- Mobile Equipment: Forklifts.\n- Installations: Lifts, heating systems.\nEmployer must ensure safety.",
                        de = "Umfasst alles: Handwerkzeuge, Maschinen, mobile Geräte, Installationen.",
                        pl = "Obejmuje wszystko: narzędzia ręczne, maszyny, sprzęt mobilny, instalacje."
                    ),
                    imageType = "policy"
                ),

                // SAYFA 2: UYGUNLUK VE STANDARTLAR (CE)
                LessonSection(
                    title = LocalizedText("Uygunluk ve CE İşareti", "Suitability & CE Mark", "Eignung & CE-Kennzeichnung", "Przydatność i Znak CE"),
                    content = LocalizedText(
                        tr = "Ekipmanlar yapılacak işe UYGUN olmalıdır.\n\n- CE İşareti: Üreticinin, ürünün Avrupa güvenlik standartlarına uyduğunu beyan ettiğini gösterir. Makinenin güvenli olduğunun ilk kanıtıdır.\n- Uygunluk Beyanı: Üretici tarafından verilen ve standartları listeleyen belge.\n- İşe Uygunluk: Bir kağıt makasıyla metal kesmeye çalışmak 'uygunsuz' kullanımdır ve risk yaratır.",
                        en = "Equipment must be SUITABLE for the task.\n- CE Mark: Manufacturer's declaration of conformity with EU safety standards.\n- Declaration of Conformity: Document listing standards.\n- Task Suitability: Don't use paper scissors to cut metal!",
                        de = "Muss geeignet sein. CE-Kennzeichnung zeigt Einhaltung der EU-Standards.",
                        pl = "Musi być odpowiedni. Znak CE oznacza zgodność z normami UE."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 3: BAKIM VE MUAYENE
                LessonSection(
                    title = LocalizedText("Bakım ve Muayene", "Maintenance & Inspection", "Wartung & Inspektion", "Konserwacja i Inspekcja"),
                    content = LocalizedText(
                        tr = "Ekipman güvenli durumda tutulmalıdır. İki tür bakım vardır:\n\n1. Önleyici Bakım (Planlı): Arıza oluşmadan önce yapılan düzenli bakım (Yağlama, parça değişimi).\n2. Arıza Bakımı (Reaktif): Makine bozulunca yapılan tamir. Güvenlik açısından risklidir.\n\nMuayene (Inspection): Yetkili kişi tarafından belirli aralıklarla yapılan görsel ve işlevsel kontroldür.",
                        en = "Two types of maintenance:\n1. Preventative (Planned): Before failure (Lubrication, replacing parts).\n2. Breakdown (Reactive): After failure. Riskier.\nInspection: Regular checks by competent person.",
                        de = "Vorbeugende Wartung (Geplant) vs. Störungsbeseitigung (Reaktiv).",
                        pl = "Konserwacja zapobiegawcza (Planowana) vs. Naprawa awarii (Reaktywna)."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 4: BİLGİ, TALİMAT VE EĞİTİM
                LessonSection(
                    title = LocalizedText("Eğitim ve Kısıtlamalar", "Training & Restrictions", "Schulung & Einschränkungen", "Szkolenie i Ograniczenia"),
                    content = LocalizedText(
                        tr = "Sadece eğitimi almış kişiler tehlikeli ekipmanları kullanmalıdır.\n\nEğitim şunları içermelidir:\n- Riskler ve tehlikeler.\n- Koruyucuların kullanımı.\n- Acil durdurma prosedürleri.\n\nÖzel Risk Altındakiler: GENÇLER (Tecrübesizler). Bazı makinelerin (örn. giyotin makas, pres) gençler tarafından kullanılması yasaktır veya sadece sıkı gözetim altında izin verilir.",
                        en = "Only trained people should use dangerous equipment.\nTraining includes: Risks, guards, emergency stops.\nSpecial Risk: YOUNG PEOPLE. Use of dangerous machinery (e.g. guillotines) is restricted or supervised.",
                        de = "Nur geschulte Personen. Einschränkungen für Jugendliche (Gefährliche Maschinen).",
                        pl = "Tylko przeszkolone osoby. Ograniczenia dla młodych (Niebezpieczne maszyny)."
                    ),
                    imageType = "warning"
                )
            )
        )
        // Konu ID'si 9.1
        neboshDao.insertTopic(Topic("9.1", "IG2", "İş Ekipmanları Genel Şartlar", "General Requirements", "Allgemeine Anforderungen", "Wymagania Ogólne", Gson().toJson(lesson9_1)))
    }
    // --- ELEMENT 8.6: İŞLE İLGİLİ SÜRÜŞ (ARAÇ GÜVENLİĞİ) ---
    private suspend fun insertElement8Topic6() {

        val lesson8_6 = TopicContent(
            sections = listOf(
                // SAYFA 1: KAPSAM VE YÖNETİM
                LessonSection(
                    title = LocalizedText("İşle İlgili Sürüş Nedir?", "What is Work-Related Driving?", "Arbeitsbezogenes Fahren", "Jazda Służbowa"),
                    content = LocalizedText(
                        tr = "İşin bir parçası olarak kamu yollarında araç kullanmaktır (Örn: Müşteri ziyareti, mal teslimatı). DİKKAT: Evden işe gidip gelmek (Commuting) buna dahil DEĞİLDİR.\n\nİşverenler, tıpkı fabrikadaki bir makine gibi, yoldaki araçları ve sürücüleri de yönetmek zorundadır. Yasal sorumlulukları vardır.",
                        en = "Driving on public roads as part of work (e.g. visiting clients, delivery). NOTE: Commuting to/from work is NOT included.\nEmployers must manage road safety just like any other workplace risk.",
                        de = "Fahren auf öffentlichen Straßen als Teil der Arbeit (Pendeln ausgeschlossen).",
                        pl = "Jazda po drogach publicznych w ramach pracy (Doazdy do pracy wykluczone)."
                    ),
                    imageType = "policy"
                ),

                // SAYFA 2: RİSK DEĞERLENDİRMESİ (SÜRÜCÜ - ARAÇ - YOLCULUK)
                LessonSection(
                    title = LocalizedText("Risk Değerlendirmesi", "Risk Assessment", "Risikobeurteilung", "Ocena Ryzyka"),
                    content = LocalizedText(
                        tr = "Riskler üç başlıkta incelenir:\n\n1. SÜRÜCÜ (Driver): Ehliyeti var mı? Deneyimli mi? Sağlığı yerinde mi (gözler)? Yorgun mu?\n2. ARAÇ (Vehicle): İşe uygun mu? Bakımı yapılmış mı? Güvenlik donanımı (ABS, Hava yastığı) var mı?\n3. YOLCULUK (Journey): Mesafe ne kadar? Hava durumu nasıl? Rota güvenli mi? Zaman baskısı var mı?",
                        en = "Assess 3 factors:\n1. DRIVER: Competency, fitness, fatigue.\n2. VEHICLE: Suitability, maintenance, safety features.\n3. JOURNEY: Distance, weather, route, time pressure.",
                        de = "3 Faktoren: Fahrer (Kompetenz), Fahrzeug (Wartung), Reise (Wetter/Zeit).",
                        pl = "3 Czynniki: Kierowca (Kompetencje), Pojazd (Konserwacja), Podróż (Pogoda/Czas)."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 3: KONTROL ÖNLEMLERİ
                LessonSection(
                    title = LocalizedText("Sürüş Risklerini Yönetmek", "Managing Risks", "Risiken managen", "Zarządzanie Ryzykiem"),
                    content = LocalizedText(
                        tr = "1. SÜRÜŞÜ ELİMİNE ET: Video konferans yap, tren veya uçak kullan.\n2. PLANLAMA: Gerçekçi teslimat süreleri belirle (Hız yapmaya zorlama). Her 2 saatte bir 15 dakika mola kuralı koy.\n3. POLİTİKA: Araç kullanırken telefonla konuşmayı yasakla.\n4. BAKIM: Araçların periyodik bakımlarını ve günlük kontrollerini (lastik, yağ) takip et.",
                        en = "1. ELIMINATE: Video calls, train/plane.\n2. PLAN: Realistic schedules (No rushing). Break every 2 hours.\n3. POLICY: Ban mobile phones while driving.\n4. MAINTENANCE: Regular service and daily checks.",
                        de = "Vermeiden (Videoanruf), Planen (Pausen), Politik (Kein Handy), Wartung.",
                        pl = "Eliminuj (Wideo), Planuj (Przerwy), Polityka (Bez telefonu), Konserwacja."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 4: ELEKTRİKLİ VE HİBRİT ARAÇLAR
                LessonSection(
                    title = LocalizedText("Elektrikli Araçlar (EV)", "Electric Vehicles (EV)", "Elektrofahrzeuge", "Pojazdy Elektryczne"),
                    content = LocalizedText(
                        tr = "Yeni teknolojiler yeni riskler getirir:\n\n- Sessiz Çalışma: Yayalar aracın geldiğini duyamaz (Özellikle otoparklarda riskli).\n- Yüksek Voltaj: Kaza veya bakım sırasında elektrik çarpması riski (Turuncu kablolar).\n- Şarj Etme: Şarj kabloları takılma tehlikesi yaratabilir veya yangın riski oluşturabilir.",
                        en = "New risks:\n- Silent Operation: Pedestrians can't hear them.\n- High Voltage: Electrocution risk during maintenance/crash.\n- Charging: Trip hazards from cables, fire risk.",
                        de = "Lautlos (Fußgänger), Hochspannung (Stromschlag), Laden (Stolpergefahr).",
                        pl = "Cicha praca (Piesi), Wysokie napięcie (Porażenie), Ładowanie (Potknięcia)."
                    ),
                    imageType = "warning"
                )
            )
        )
        // Konu ID'si 8.6
        neboshDao.insertTopic(Topic("8.6", "IG2", "İşle İlgili Sürüş", "Work-Related Driving", "Arbeitsbezogenes Fahren", "Jazda Służbowa", Gson().toJson(lesson8_6)))
    }
    // --- ELEMENT 8.4: KAPALI ALANLAR ---
    private suspend fun insertElement8Topic4() {

        val lesson8_4 = TopicContent(
            sections = listOf(
                // SAYFA 1: KAPALI ALAN NEDİR?
                LessonSection(
                    title = LocalizedText("Kapalı Alan Nedir?", "What is a Confined Space?", "Enger Raum", "Przestrzeń Zamknięta"),
                    content = LocalizedText(
                        tr = "Kapalı alan, tamamen veya kısmen kapalı olan ve içinde ciddi yaralanma riski barındıran yerlerdir. İlla küçük olması gerekmez.\n\nÖrnekler:\n- Depolama tankları ve silolar.\n- Kanalizasyonlar ve kuyular.\n- Açık üstlü odalar (gaz çökerse).\n- Gemi ambarları.\n- Tüneller.",
                        en = "A confined space is substantially enclosed and carries a foreseeable risk of serious injury. It doesn't have to be small.\nExamples:\n- Tanks and silos.\n- Sewers and wells.\n- Open-topped chambers.\n- Ship holds.\n- Tunnels.",
                        de = "Teilweise oder ganz geschlossener Raum mit Risiken. Tanks, Silos, Kanäle.",
                        pl = "Przestrzeń częściowo lub całkowicie zamknięta z ryzykiem. Zbiorniki, silosy, kanały."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: TEHLİKELER
                LessonSection(
                    title = LocalizedText("Tehlikeler", "Hazards", "Gefahren", "Zagrożenia"),
                    content = LocalizedText(
                        tr = "Kapalı alanlarda ölümlerin çoğu 'Kurtarmaya Çalışanlar'dan oluşur!\n\nAna Tehlikeler:\n1. Oksijen Eksikliği: Paslanma veya bakteriler oksijeni tüketir. (Hissedilmez, anında bayıltır).\n2. Zehirli Gazlar: H2S (Kanalizasyon gazı), CO (Egzoz).\n3. Yangın/Patlama: Yanıcı gaz birikmesi.\n4. Boğulma: İçeriye sıvı veya katı (tahıl/kum) akışı.\n5. Isı Stresi: Sıcak ve havasız ortam.",
                        en = "Most deaths involve 'Would-be Rescuers'!\nHazards:\n1. Oxygen Deficiency: Rust/bacteria consume O2.\n2. Toxic Gases: H2S, CO.\n3. Fire/Explosion.\n4. Drowning: Ingress of liquid/solids.\n5. Heat Stress.",
                        de = "Sauerstoffmangel, giftige Gase, Feuer/Explosion, Ertrinken (Flüssigkeit/Feststoff).",
                        pl = "Brak tlenu, toksyczne gazy, pożar/wybuch, utonięcie (ciecz/ciało stałe)."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: KONTROL ÖNLEMLERİ
                LessonSection(
                    title = LocalizedText("Kontrol Önlemleri", "Control Measures", "Kontrollmaßnahmen", "Środki Kontroli"),
                    content = LocalizedText(
                        tr = "1. GİRİŞİ ENGELLE: İşi dışarıdan yapabilir misin? (Örn: Kameralı inceleme).\n2. ÇALIŞMA İZNİ (PTW): İzin belgesi olmadan giriş YASAK.\n3. GAZ ÖLÇÜMÜ: Girmeden önce atmosferi test et.\n4. HAVALANDIRMA: İçeriyi temiz hava ile besle.\n5. GÖZCÜ (Sentry): Dışarıda bekleyen, içeriyle iletişim kuran kişi.\n6. ACİL DURUM PLANI: Vinç sistemi, solunum cihazları. ASLA maskesiz içeri girme!",
                        en = "1. AVOID ENTRY: Use cameras?\n2. PERMIT TO WORK: Mandatory.\n3. GAS TESTING: Test atmosphere.\n4. VENTILATION: Purge air.\n5. SENTRY: Watchman outside.\n6. EMERGENCY PLAN: Winch, BA sets. NEVER enter without protection!",
                        de = "Vermeiden, Arbeitserlaubnis, Gastest, Lüftung, Wache, Notfallplan.",
                        pl = "Unikaj, Pozwolenie na pracę, Test gazu, Wentylacja, Wartownik, Plan awaryjny."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 8.4
        neboshDao.insertTopic(Topic("8.4", "IG2", "Kapalı Alanlar", "Confined Spaces", "Enge Räume", "Przestrzenie Zamknięte", Gson().toJson(lesson8_4)))
    }

    // --- ELEMENT 8.5: YALNIZ ÇALIŞMA ---
    private suspend fun insertElement8Topic5() {

        val lesson8_5 = TopicContent(
            sections = listOf(
                // SAYFA 1: YALNIZ ÇALIŞAN KİMDİR?
                LessonSection(
                    title = LocalizedText("Yalnız Çalışan Kimdir?", "Who is a Lone Worker?", "Alleinarbeiter", "Pracownik Samotny"),
                    content = LocalizedText(
                        tr = "Doğrudan gözetim olmadan çalışanlardır.\n\nÖrnekler:\n- Ofiste tek kalan temizlikçiler.\n- Gece bekçileri.\n- Bakımcılar.\n- Sürücüler (Kamyon şoförleri).\n- Ev ziyaretine giden sağlıkçılar.\n\nYalnız çalışmak yasadışı değildir ancak özel risk değerlendirmesi gerektirir.",
                        en = "Those who work without close supervision.\nExamples: Cleaners, security guards, maintenance staff, drivers, home carers.\nNot illegal, but requires specific risk assessment.",
                        de = "Arbeitet ohne direkte Aufsicht. Reinigungskräfte, Wachpersonal, Fahrer.",
                        pl = "Pracuje bez bezpośredniego nadzoru. Sprzątacze, ochroniarze, kierowcy."
                    ),
                    imageType = "policy"
                ),

                // SAYFA 2: RİSKLER VE KONTROLLER
                LessonSection(
                    title = LocalizedText("Riskler ve Kontroller", "Risks & Controls", "Risiken & Kontrollen", "Ryzyko i Kontrole"),
                    content = LocalizedText(
                        tr = "Özel Riskler:\n- Kaza geçirirse yardım edememe.\n- Şiddet (Saldırıya uğrama).\n- Ani hastalık.\n\nKontroller:\n- İLETİŞİM: Düzenli telefon/telsiz kontrolü (Örn: Her saat başı arama).\n- TEKNOLOJİ: Panik butonları, 'Man-down' (hareketsizlik) alarmları.\n- KISITLAMA: Bazı işler yalnız YAPILAMAZ (Kapalı alan, elektrik, merdiven).\n- EĞİTİM: Saldırganlıkla başa çıkma eğitimi.",
                        en = "Risks: No help if injured, Violence, Illness.\nControls:\n- COMMUNICATION: Regular check-ins.\n- TECH: Panic buttons, Man-down alarms.\n- RESTRICTIONS: No high-risk work alone (confined space, electric).\n- TRAINING: Defusing aggression.",
                        de = "Kommunikation (Check-ins), Panikknöpfe, Einschränkungen (keine Hochrisikoarbeiten), Training.",
                        pl = "Komunikacja (Check-iny), Przyciski paniki, Ograniczenia (brak prac wysokiego ryzyka), Szkolenie."
                    ),
                    imageType = "audit"
                )
            )
        )
        // Konu ID'si 8.5
        neboshDao.insertTopic(Topic("8.5", "IG2", "Yalnız Çalışma", "Lone Working", "Alleinarbeit", "Praca Samotna", Gson().toJson(lesson8_5)))
    }
    // --- ELEMENT 8.3: YÜKSEKTE ÇALIŞMA ---
    private suspend fun insertElement8Topic3() {

        val lesson8_3 = TopicContent(
            sections = listOf(
                // SAYFA 1: HİYERARŞİ (AVOID - PREVENT - MINIMISE)
                LessonSection(
                    title = LocalizedText("Yüksekte Çalışma Hiyerarşisi", "Hierarchy of Control", "Hierarchie", "Hierarchia"),
                    content = LocalizedText(
                        tr = "Yüksekte çalışma, kişinin düşerek yaralanabileceği herhangi bir yerdir (yer seviyesinde bir çukur dahil). Riskleri yönetmek için şu sıra izlenmelidir:\n\n1. KAÇIN (Avoid): İşi yüksekte yapma (Örn: Camları uzun saplı silecek ile yerden temizle).\n2. ÖNLE (Prevent): Düşmeyi önle (Korkuluklu iskele veya sepetli platform kullan).\n3. AZALT (Minimise): Düşme ihtimali varsa, mesafeyi veya sonucu azalt (Hava yastıkları, güvenlik ağları, emniyet kemeri).",
                        en = "Hierarchy:\n1. AVOID: Work from ground (e.g., long-handled tools).\n2. PREVENT: Use guardrails, MEWPs, scaffolds.\n3. MINIMISE: Reduce distance/consequence (Safety nets, airbags, harnesses).",
                        de = "Vermeiden (Bodenarbeit), Verhindern (Geländer), Minimieren (Netze/Gurte).",
                        pl = "Unikaj (Praca z ziemi), Zapobiegaj (Barierki), Minimalizuj (Siatki/Szelki)."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 2: MERDİVEN GÜVENLİĞİ
                LessonSection(
                    title = LocalizedText("Merdiven Güvenliği", "Safe Use of Ladders", "Leitern", "Drabiny"),
                    content = LocalizedText(
                        tr = "Merdivenler sadece 'Kısa Süreli' (maks. 30 dk) ve 'Hafif İşler' için son çare olarak kullanılmalıdır.\n\nKurallar:\n- 3 Nokta Teması: Her zaman iki el bir ayak veya iki ayak bir el merdivende olmalı.\n- Açı: 1'e 4 kuralı (4 birim yukarı için 1 birim dışarı).\n- Sabitleme: Üstten bağlanmalı veya alttan desteklenmeli.\n- Durum: Basamaklar temiz ve sağlam olmalı.",
                        en = "Ladders are a last resort for Short Duration (max 30 mins) & Light Work.\nRules:\n- 3 Points of Contact.\n- Angle: 1 out for every 4 up (75°).\n- Secured: Tied at top or footed at bottom.\n- Condition: Clean and undamaged rungs.",
                        de = "Nur kurzzeitig (30 Min). 3-Punkt-Kontakt. Winkel 1:4. Gesichert.",
                        pl = "Krótki czas (30 min). 3 punkty podparcia. Kąt 1:4. Zabezpieczone."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: İSKELE GÜVENLİĞİ
                LessonSection(
                    title = LocalizedText("İskele Güvenliği", "Scaffolding Safety", "Gerüstbau", "Rusztowania"),
                    content = LocalizedText(
                        tr = "Güvenli bir iskelenin bileşenleri:\n\n1. Taban: Sağlam zemin üzerinde taban plakaları (base plates).\n2. Korkuluklar: Çift korkuluk (üst ve orta) olmalı. Düşmeyi önler.\n3. Tekmelik (Toe-board): Malzemelerin aşağı düşmesini önler.\n4. Platform: Tamamen kalaslarla kaplı olmalı, boşluk olmamalı.\n5. Muayene: Kurulduktan sonra, her 7 günde bir ve kötü hava koşullarından sonra kontrol edilmelidir.",
                        en = "Key components:\n1. Base: Base plates on firm ground.\n2. Guardrails: Double rails (Top & Mid) to prevent falls.\n3. Toe-boards: Prevent objects falling.\n4. Platform: Fully boarded.\n5. Inspection: After erection, every 7 days, and after bad weather.",
                        de = "Fußplatten, Geländer, Bordbretter. Inspektion alle 7 Tage.",
                        pl = "Podstawy, Barierki, Krawężniki. Inspekcja co 7 dni."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 4: KIRILGAN ÇATILAR VE DÜŞEN CİSİMLER
                LessonSection(
                    title = LocalizedText("Kırılgan Çatılar ve Cisimler", "Fragile Roofs & Objects", "Zerbrechliche Dächer", "Kruche Dachy"),
                    content = LocalizedText(
                        tr = "Kırılgan Çatılar: Asbestli çimento levhalar veya çatı pencereleri (skylights) üzerine basıldığında kırılabilir. Üzerinde yürümek için 'Yürüme Yolları' (Crawling boards) kullanılmalıdır.\n\nDüşen Cisimler: Aşağıdakileri korumak için;\n- Tekmelik kullanın.\n- Ağ veya kaplama (brick guards) kullanın.\n- Aşağıdaki alanı barikatla kapatın.\n- Baret takın.",
                        en = "Fragile Roofs: Use crawling boards/roof ladders. Never step directly on skylights.\n\nFalling Objects: Use toe-boards, debris netting, exclusion zones (barriers below), and wear hard hats.",
                        de = "Laufbretter für zerbrechliche Dächer. Schuttnetze und Helme gegen herabfallende Gegenstände.",
                        pl = "Deski do chodzenia na kruche dachy. Siatki i kaski przeciw spadającym przedmiotom."
                    ),
                    imageType = "hazard"
                )
            )
        )
        // Konu ID'si 8.3
        neboshDao.insertTopic(Topic("8.3", "IG2", "Yüksekte Çalışma", "Working at Height", "Arbeiten in der Höhe", "Praca na Wysokości", Gson().toJson(lesson8_3)))
    }
    // --- ELEMENT 8.2: YÜRÜYÜŞ YOLLARI (KAYMA VE TAKILMALAR) ---
    private suspend fun insertElement8Topic2() {

        val lesson8_2 = TopicContent(
            sections = listOf(
                // SAYFA 1: TANIMLAR VE İSTATİSTİKLER
                LessonSection(
                    title = LocalizedText("Kayma ve Takılma Nedir?", "Slips & Trips Definitions", "Ausrutschen & Stolpern", "Poślizgnięcia i Potknięcia"),
                    content = LocalizedText(
                        tr = "İşyeri yaralanmalarının en yaygın nedenidir (Kırık kemikler, burkulmalar).\n\n1. KAYMA (Slip): Ayakkabı ile zemin arasındaki sürtünmenin (tutunmanın) kaybolmasıdır. Genellikle ıslak veya kaygan zeminlerden kaynaklanır.\n2. TAKILMA (Trip): Ayağın bir engele çarpması veya takılmasıdır. Genellikle dağınık ortamdan kaynaklanır.",
                        en = "Most common cause of injuries.\n1. SLIP: Loss of friction between shoe and floor. Usually wet surfaces.\n2. TRIP: Foot hits an obstacle. Usually poor housekeeping.",
                        de = "Ausrutschen (Reibungsverlust) und Stolpern (Hindernis). Häufigste Unfallursache.",
                        pl = "Poślizgnięcie (utrata tarcia) i Potknięcie (przeszkoda). Najczęstsza przyczyna wypadków."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 2: KAYMA NEDENLERİ
                LessonSection(
                    title = LocalizedText("Kayma Nedenleri", "Causes of Slips", "Ursachen für Ausrutschen", "Przyczyny Poślizgnięć"),
                    content = LocalizedText(
                        tr = "Kaymaların ana nedenleri:\n\n- Zemin Kirliliği: Dökülen su, yağ, sabun veya toz (kuru toz da kaygandır).\n- Zemin Yapısı: İş için yanlış zemin seçimi (Parlak fayans vs.).\n- Temizlik Süreci: Islak temizlik yaparken uyarı levhası koymamak.\n- Ayakkabı: Tabanı aşınmış veya işe uygun olmayan (kaymaz taban olmayan) ayakkabılar.\n- Çevre: Yağmur, buz, kar (dış alanlar).",
                        en = "Causes of Slips:\n- Contamination: Water, oil, dust.\n- Floor Surface: Wrong type (Shiny tiles).\n- Cleaning: Wet mopping without signs.\n- Footwear: Worn soles, non-grip shoes.\n- Environment: Rain, ice, snow.",
                        de = "Verschmutzung (Wasser/Öl), Falscher Boden, Reinigung, Schlechtes Schuhwerk, Umwelt (Eis).",
                        pl = "Zanieczyszczenie (Woda/Olej), Zła podłoga, Sprzątanie, Złe obuwie, Środowisko (Lód)."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 3: TAKILMA NEDENLERİ
                LessonSection(
                    title = LocalizedText("Takılma Nedenleri", "Causes of Trips", "Ursachen für Stolpern", "Przyczyny Potknięć"),
                    content = LocalizedText(
                        tr = "Takılmaların ana nedenleri:\n\n- Kablolar: Yerde sürünen uzatma kabloları.\n- Düzensizlik (Housekeeping): Yerde bırakılan kutular, aletler, çöpler.\n- Zemin Hasarı: Kalkmış halı kenarları, kırık fayanslar, kot farkları (eşikler).\n- Aydınlatma: Yetersiz ışık nedeniyle engellerin görülememesi.",
                        en = "Causes of Trips:\n- Trailing cables.\n- Poor Housekeeping: Objects on floor.\n- Floor Damage: Raised carpets, broken tiles, uneven steps.\n- Lighting: Cannot see obstacles.",
                        de = "Kabel, Unordnung, Bodenschäden, Schlechte Beleuchtung.",
                        pl = "Kable, Nieporządek, Uszkodzenia podłogi, Słabe oświetlenie."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 4: KONTROL ÖNLEMLERİ
                LessonSection(
                    title = LocalizedText("Kontrol Önlemleri", "Control Measures", "Kontrollmaßnahmen", "Środki Kontroli"),
                    content = LocalizedText(
                        tr = "Riskleri önlemek için:\n\n1. Temizlik ve Düzen: 'Her şeyin bir yeri olmalı ve her şey yerinde olmalı'.\n2. Kablo Yönetimi: Kabloları sabitleyin veya kablo kanalı kullanın.\n3. Zemin Bakımı: Hasarlı zeminleri hemen onarın. Dökülenleri anında temizleyin.\n4. Kaymaz Taban: Uygun iş ayakkabısı (KKD) sağlayın.\n5. İşaretleme: Islak zeminlerde 'Dikkat Kaygan Zemin' tabelası kullanın.",
                        en = "1. Good Housekeeping: 'A place for everything'.\n2. Cable Management: Use covers/ties.\n3. Maintenance: Repair floors, clean spills immediately.\n4. Non-slip Footwear (PPE).\n5. Signage: 'Wet Floor' signs.",
                        de = "Ordnung, Kabelmanagement, Wartung, Rutschfeste Schuhe, Warnschilder.",
                        pl = "Porządek, Zarządzanie kablami, Konserwacja, Obuwie antypoślizgowe, Znaki ostrzegawcze."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 8.2
        neboshDao.insertTopic(Topic("8.2", "IG2", "Yürüyüş Yolları (Kayma/Takılma)", "Safe Walking Routes (Slips/Trips)", "Sichere Wege", "Bezpieczne Drogi", Gson().toJson(lesson8_2)))
    }
    // --- ELEMENT 8: GENEL İŞYERİ SORUNLARI ---
    private suspend fun insertElement8Topics() {
        // KONU 8.1: SAĞLIK, REFAH VE ÇALIŞMA ORTAMI
        val lesson8_1 = TopicContent(
            sections = listOf(
                // SAYFA 1: REFAH TESİSLERİ (WELFARE)
                LessonSection(
                    title = LocalizedText("Refah Tesisleri", "Welfare Facilities", "Sozialeinrichtungen", "Zaplecze Socjalne"),
                    content = LocalizedText(
                        tr = "İşveren, çalışanların temel insani ihtiyaçlarını karşılamak zorundadır:\n\n1. TUVALETLER: Yeterli sayıda, temiz, aydınlatılmış, havalandırılmış olmalı. Kadın/Erkek ayrılmalı. Sıcak/soğuk su ve sabun bulunmalı.\n2. İÇME SUYU: Temiz ve erişilebilir olmalı ('İçilebilir' diye işaretlenmeli).\n3. YEMEK VE DİNLENME: Yemeğini yiyebileceği hijyenik bir alan. Hamileler ve emziren anneler için uzanıp dinlenebilecekleri özel alanlar.\n4. GİYİNME ODALARI: İş kıyafeti giyiliyorsa soyunma dolapları sağlanmalı.",
                        en = "Employers must provide:\n1. TOILETS: Clean, lit, ventilated. Separate M/F. Hot/cold water, soap.\n2. DRINKING WATER: Clean, accessible, marked.\n3. REST/EATING: Area to eat. Rest facilities for pregnant women/nursing mothers.\n4. CHANGING ROOMS: Lockers for work clothes.",
                        de = "Toiletten, Trinkwasser, Essbereich, Ruhebereich (Schwangere), Umkleideräume.",
                        pl = "Toalety, Woda pitna, Jadalnia, Odpoczynek (Ciąża), Szatnie."
                    ),
                    imageType = "policy"
                ),

                // SAYFA 2: ÇALIŞMA ORTAMI (SICAKLIK)
                LessonSection(
                    title = LocalizedText("Sıcaklık ve Termal Konfor", "Temperature & Thermal Comfort", "Temperatur", "Temperatura"),
                    content = LocalizedText(
                        tr = "İşyeri ne çok sıcak ne de çok soğuk olmalıdır.\n\n- Minimum Sıcaklık: Ofisler için 16°C, fiziksel işler için 13°C.\n- Termal Konforu Etkileyenler: Sadece hava sıcaklığı değil; Nem, Hava Hızı (Ceryan), Radyan Isı (Fırın/Güneş) ve yapılan işin ağırlığı.\n\nRiskler:\n- Çok Sıcak: Dehidrasyon, Isı çarpması, Kas krampları.\n- Çok Soğuk: Hipotermi, Donma, Konsantrasyon kaybı (Kaza riski).",
                        en = "Min temp: 16°C (Office), 13°C (Physical work).\nThermal Comfort Factors: Temp, Humidity, Air velocity, Radiant heat.\nRisks:\n- HOT: Dehydration, Heat stroke.\n- COLD: Hypothermia, Frostbite, Loss of concentration.",
                        de = "Min: 16°C/13°C. Faktoren: Temp, Feuchtigkeit, Luftzug. Risiken: Hitzschlag, Unterkühlung.",
                        pl = "Min: 16°C/13°C. Czynniki: Temp, Wilgotność, Przewiew. Ryzyko: Udar cieplny, Hipotermia."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 3: AYDINLATMA (IŞIKLANDIRMA)
                LessonSection(
                    title = LocalizedText("Aydınlatma", "Lighting", "Beleuchtung", "Oświetlenie"),
                    content = LocalizedText(
                        tr = "İyi aydınlatma kazaları ve göz yorgunluğunu önler. Mümkünse 'Doğal Işık' tercih edilmelidir.\n\nSorunlar:\n- Yetersiz Işık: Takılma/Düşme riski, göz yorgunluğu.\n- Parlama (Glare): Ekranı veya işi görmeyi engeller.\n- Stroboskopik Etki: Floresan lambaların titreşimi, dönen makine parçalarını 'duruyormuş gibi' gösterebilir (Çok tehlikeli!).\n- Titreme (Flicker): Baş ağrısı ve epilepsi tetikleyebilir.",
                        en = "Natural light is best.\nProblems:\n- Low Light: Trip hazards, eye strain.\n- Glare: Blinds vision.\n- Stroboscopic Effect: Rotating machinery looks stationary (Fluorescent lights).\n- Flicker: Headaches.",
                        de = "Natürliches Licht bevorzugt. Probleme: Blendung, Stroboskop-Effekt (Maschinen wirken stillstehend).",
                        pl = "Naturalne światło najlepsze. Problemy: Odblaski, Efekt stroboskopowy (Maszyny wydają się stać)."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 4: ALAN VE TEMİZLİK
                LessonSection(
                    title = LocalizedText("Alan ve Düzen", "Space & Housekeeping", "Raum & Ordnung", "Przestrzeń & Porządek"),
                    content = LocalizedText(
                        tr = "Yeterli Çalışma Alanı: İşçiler sıkışık çalışmamalıdır. Oda hacmi kişi başı en az 11 metreküp olmalıdır.\n\nTemizlik ve Düzen (Housekeeping):\n- Zeminler temiz ve kuru tutulmalı.\n- Koridorlar (kaçış yolları) malzeme ile kapatılmamalı.\n- Çöpler düzenli atılmalı.\n- Kablolar toplanmalı (Takılma riski).\n\n'Temiz bir işyeri, güvenli bir işyeridir.'",
                        en = "Space: Min 11 cubic metres per person.\nHousekeeping:\n- Floors clean/dry.\n- Corridors clear (Escape routes).\n- Waste removed.\n- Cables tidied.\n'A tidy workplace is a safe workplace.'",
                        de = "Min 11m³ pro Person. Ordnung: Böden sauber, Wege frei, Kabel weg.",
                        pl = "Min 11m³ na osobę. Porządek: Podłogi czyste, Drogi wolne, Kable uprzątnięte."
                    ),
                    imageType = "audit"
                )
            )
        )
        // Konu ID'si 8.1
        neboshDao.insertTopic(Topic("8.1", "IG2", "Sağlık ve Refah", "Health, Welfare & Work Environment", "Gesundheit & Arbeitsumgebung", "Zdrowie i Środowisko Pracy", Gson().toJson(lesson8_1)))
    }
    // --- ELEMENT 7.4: SPESİFİK AJANLAR (ASBEST, LEJYONELLA VB.)
    private suspend fun insertElement7Topic4() {

        val lesson7_4 = TopicContent(
            sections = listOf(
                // SAYFA 1: ASBEST (EN BÜYÜK KATİL)
                LessonSection(
                    title = LocalizedText("Asbest", "Asbestos", "Asbest", "Azbest"),
                    content = LocalizedText(
                        tr = "Asbest, ısıya ve ateşe dayanıklı doğal bir mineraldir (eski binalarda yalıtım için kullanılmıştır). Lifleri solunduğunda ölümcüldür.\n\nTürleri: Krizotil (Beyaz), Amozit (Kahverengi), Krokidolit (Mavi - En tehlikeli).\n\nHastalıklar:\n- Asbestozis: Akciğer dokusunun sertleşmesi (Nefes darlığı).\n- Akciğer Kanseri.\n- Mezotelyoma: Akciğer zarının kanseri (Sadece asbeste özgüdür ve tedavisi yoktur).\n\nKontrol: Asbestli malzemeyi rahatsız etmeyin. Söküm için lisanslı uzmanlar gerekir.",
                        en = "Asbestos is a fire-resistant mineral. Deadly if fibres are inhaled.\nTypes: White, Brown, Blue (Most dangerous).\nDiseases:\n- Asbestosis: Scarring of lungs.\n- Lung Cancer.\n- Mesothelioma: Cancer of the lung lining (Incurable, unique to asbestos).\nControl: Do not disturb. Requires licensed removal.",
                        de = "Asbest verursacht Asbestose und Mesotheliom (Lungenfellkrebs).",
                        pl = "Azbest powoduje pylicę azbestową i międzybłoniaka (rak opłucnej)."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: LEJYONELLA BAKTERİSİ
                LessonSection(
                    title = LocalizedText("Lejyonella (Su Sistemleri)", "Legionella", "Legionellen", "Legionella"),
                    content = LocalizedText(
                        tr = "Su sistemlerinde (soğutma kuleleri, duş başlıkları) üreyen bir bakteridir. Solunduğunda 'Lejyoner Hastalığı'na (zatürre türü) neden olur.\n\nRiskli Ortam: 20°C ile 45°C arası durgun su ve pas/tortu varlığı.\n\nKontrol (Sıcaklık Yönetimi):\n- Sıcak su 60°C'de saklanmalı, musluktan en az 50°C akmalı.\n- Soğuk su 20°C'nin altında tutulmalı.\n- Sistemdeki durgun sular düzenli akıtılmalı ve kireç temizlenmeli.",
                        en = "Bacteria in water systems (cooling towers, showers). Causes Legionnaires' disease (Pneumonia).\nThrives in: Stagnant water between 20°C - 45°C.\nControl:\n- Keep Hot water > 60°C.\n- Keep Cold water < 20°C.\n- Flush stagnant outlets, remove scale.",
                        de = "Bakterien im Wasser. Kontrolle durch Temperatur: Heiß >60°C, Kalt <20°C.",
                        pl = "Bakterie w wodzie. Kontrola temperatury: Gorąca >60°C, Zimna <20°C."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: SİLİKA VE ÇİMENTO
                LessonSection(
                    title = LocalizedText("Silika ve Çimento", "Silica & Cement", "Silizium & Zement", "Krzemionka & Cement"),
                    content = LocalizedText(
                        tr = "SİLİKA TOZU: Taş, beton, kum kesilirken çıkar. Solunması 'Silikozis' (akciğer sertleşmesi) yapar. Maske (FFP3) ve su ile bastırma (ıslak kesim) şarttır.\n\nÇİMENTO TOZU: İki ana riski vardır:\n1. Tahriş edici toz (Solunum).\n2. Kimyasal Yanık: Islak çimento alkalidir (bazik), cildi yakar ve 'Dermatit'e neden olur. Temastan sonra hemen yıkanmalıdır.",
                        en = "SILICA: From cutting stone/concrete. Causes Silicosis. Use wet cutting/masks.\n\nCEMENT: Wet cement is Alkali. Causes chemical burns and Dermatitis. Wash off immediately.",
                        de = "Silika (Silikose durch Steinstaub). Zement (Alkali - Verätzungen und Dermatitis).",
                        pl = "Krzemionka (Krzemica od pyłu kamiennego). Cement (Alkaliczny - oparzenia i zapalenie skóry)."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 4: KARBONMONOKSİT (CO) VE LEPTOSPİROZ
                LessonSection(
                    title = LocalizedText("Karbonmonoksit ve Leptospiroz", "CO & Leptospirosis", "CO & Leptospirose", "CO & Leptospiroza"),
                    content = LocalizedText(
                        tr = "KARBONMONOKSİT (CO): Yakıtların tam yanmamasıyla oluşur. Renksiz, kokusuzdur ('Sessiz Katil'). Kandaki oksijeni engeller ve boğar. (Örn: Kapalı alanda çalışan benzinli motorlar).\n\nLEPTOSPİROZ (Weil Hastalığı): Fare idrarıyla bulaşan bir bakteridir. Kanalizasyon işçileri ve çiftçiler risk altındadır. Grip benzeri başlar, sarılık ve karaciğer yetmezliğine döner. Hijyen ve yara kapatma önemlidir.",
                        en = "CO: Colourless, odourless gas from incomplete combustion. Silent killer (Asphyxiant).\n\nLEPTOSPIROSIS (Weil's Disease): From rat urine. Risk for sewage workers/farmers. Causes jaundice/liver failure.",
                        de = "CO (Geruchloses Gas - Ersticken). Leptospirose (Rattenurin - Weil-Krankheit).",
                        pl = "CO (Bezwonny gaz - Duszenie). Leptospiroza (Mocz szczurów - Choroba Weila)."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 7.4
        neboshDao.insertTopic(Topic("7.4", "IG2", "Spesifik Ajanlar", "Specific Agents", "Spezifische Stoffe", "Określone Czynniki", Gson().toJson(lesson7_4)))
    }
    // --- ELEMENT 7.3: KONTROL ÖNLEMLERİ (LEV & RPE) ---
    private suspend fun insertElement7Topic3() {

        val lesson7_3 = TopicContent(
            sections = listOf(
                // SAYFA 1: KONTROL HİYERARŞİSİ
                LessonSection(
                    title = LocalizedText("Kontrol Hiyerarşisi", "Hierarchy of Control", "Hierarchie der Kontrolle", "Hierarchia Kontroli"),
                    content = LocalizedText(
                        tr = "Tehlikeli maddeler için hiyerarşi:\n\n1. ELİMİNASYON: Maddeyi kullanma.\n2. İKAME (Substitution): Daha az tehlikeli olanla değiştir (Toz yerine granül, solvent bazlı yerine su bazlı boya).\n3. MÜHENDİSLİK KONTROLLERİ: İşlemi kapalı sisteme al (Enclosure) veya havalandırma (LEV) kullan.\n4. İDARİ KONTROLLER: Maruziyet süresini azalt, yeme-içme yasağı.\n5. KKD (PPE): Solunum koruyucu (RPE) ve eldiven.",
                        en = "Hierarchy:\n1. ELIMINATION: Don't use it.\n2. SUBSTITUTION: Use safer alternative (Granules vs dust, water-based vs solvent).\n3. ENGINEERING: Enclosure or LEV.\n4. ADMIN: Reduce time, no eating.\n5. PPE: RPE and gloves.",
                        de = "Eliminierung, Substitution, Technische Maßnahmen (LEV), Organisatorische Maßnahmen, PSA.",
                        pl = "Eliminacja, Substytucja, Środki techniczne (LEV), Środki organizacyjne, ŚOI."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 2: LOKAL EGZOZ HAVALANDIRMA (LEV)
                LessonSection(
                    title = LocalizedText("Lokal Egzoz Havalandırma (LEV)", "Local Exhaust Ventilation (LEV)", "Lokale Absaugung", "Miejscowa Wentylacja Wyciągowa"),
                    content = LocalizedText(
                        tr = "LEV, kirleticiyi kaynağında yakalayan sistemdir. 5 ana parçadan oluşur:\n\n1. DAVLUMBAZ (Hood): Kirleticiyi toplar (Kaynağa en yakın olmalı).\n2. KANAL (Ducting): Kirli havayı taşır.\n3. HAVA TEMİZLEYİCİ (Air Cleaner/Filter): Kirleticiyi havadan süzer.\n4. FAN: Hava akışını sağlar (Motor).\n5. EGZOZ ÇIKIŞI (Exhaust Outlet): Temizlenen havayı güvenli bir yere (dışarıya) atar.",
                        en = "LEV captures contaminant at source. 5 Parts:\n1. HOOD: Collects (Must be close to source).\n2. DUCTING: Transports air.\n3. FILTER: Removes contaminant.\n4. FAN: Moves air.\n5. EXHAUST OUTLET: Discharges to safe place.",
                        de = "5 Teile: Haube, Kanal, Filter, Ventilator, Auslass.",
                        pl = "5 Części: Okap, Kanał, Filtr, Wentylator, Wylot."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 3: GENEL (SEYRELTME) HAVALANDIRMA
                LessonSection(
                    title = LocalizedText("Seyreltme Havalandırması", "Dilution Ventilation", "Verdünnungslüftung", "Wentylacja Rozcieńczająca"),
                    content = LocalizedText(
                        tr = "Temiz hava vererek kirli havayı seyrelten sistemdir (Pencere açmak veya fan). Sadece şu durumlarda uygundur:\n\n- Madde düşük toksisitede ise (Zehirli değilse).\n- Miktar az ise.\n- Kirletici gaz veya buhar ise (Toz için uygun değildir, çünkü tozu dağıtır).\n\nYüksek toksik maddeler için MUTLAKA LEV kullanılmalıdır.",
                        en = "Dilutes contaminant with fresh air. Only suitable if:\n- Low toxicity.\n- Small amount.\n- Gas or vapour (NOT for dust - it spreads it).\nFor high toxicity, use LEV.",
                        de = "Nur für geringe Toxizität und Gase/Dämpfe. Nicht für Staub!",
                        pl = "Tylko dla niskiej toksyczności i gazów/par. Nie dla pyłu!"
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 4: SOLUNUM KORUYUCU DONANIM (RPE)
                LessonSection(
                    title = LocalizedText("Solunum Koruyucular (RPE)", "Respiratory Protective Equipment (RPE)", "Atemschutz", "Sprzęt Ochrony Dróg Oddechowych"),
                    content = LocalizedText(
                        tr = "İki ana türe ayrılır:\n1. SOLUNUM CİHAZLARI (Respirators): Ortamdaki havayı filtreler. Oksijen eksikliği varsa KULLANILAMAZ.\n2. HAVA BESLEMELİ CİHAZLAR (Breathing Apparatus): Temiz hava kaynağından (tüp) hava verir. Oksijensiz ortamda kullanılabilir.\n\nÖnemli: Maske kullanıcının yüzüne tam oturmalıdır. Bunun için 'Yüz Uygunluk Testi' (Face Fit Test) yapılmalıdır. Sakal, sızdırmazlığı bozar!",
                        en = "1. RESPIRATORS: Filter air. Cannot use if O2 is low.\n2. BREATHING APPARATUS (BA): Supplies air from tank. Use in low O2.\nImportant: 'Face Fit Test' is required. Beards break the seal!",
                        de = "Filtergeräte (nicht bei O2-Mangel) vs. Atemschutzgeräte (Flaschen). Dichtsitzprüfung (Kein Bart!).",
                        pl = "Maski filtrujące (nie przy braku O2) vs. Aparaty oddechowe. Test dopasowania (Bez brody!)."
                    ),
                    imageType = "warning"
                )
            )
        )
        // Konu ID'si 7.3
        neboshDao.insertTopic(Topic("7.3", "IG2", "Kontrol Önlemleri (LEV & RPE)", "Control Measures (LEV & RPE)", "Kontrollmaßnahmen", "Środki Kontroli", Gson().toJson(lesson7_3)))
    }
    // --- ELEMENT 7.2: SAĞLIK RİSKLERİNİN DEĞERLENDİRİLMESİ ---
    private suspend fun insertElement7Topic2() {

        val lesson7_2 = TopicContent(
            sections = listOf(
                // SAYFA 1: VÜCUDA GİRİŞ YOLLARI
                LessonSection(
                    title = LocalizedText("Vücuda Giriş Yolları", "Routes of Entry", "Aufnahmewege", "Drogi Wnikania"),
                    content = LocalizedText(
                        tr = "Tehlikeli maddelerin vücuda girmesinin 4 ana yolu vardır:\n\n1. SOLUMA (Inhalation): En yaygın yoldur. Gaz, toz veya dumanın nefesle ciğerlere çekilmesi.\n2. EMİLİM (Absorption): Deri yoluyla geçiş. Bazı kimyasallar (örn. solventler) deriden kana karışabilir.\n3. YUTMA (Ingestion): Kirli ellerle yemek yemek veya sigara içmek. Yanlışlıkla kimyasal içmek.\n4. ENJEKSİYON (Injection): Kesici/delici aletlerin deriyi delip maddeyi kana karıştırması (örn. iğne batması).",
                        en = "4 Routes of Entry:\n1. INHALATION: Most common. Breathing in gas/dust.\n2. ABSORPTION: Through the skin (e.g. solvents).\n3. INGESTION: Eating with dirty hands or swallowing chemicals.\n4. INJECTION: Sharp objects piercing skin (e.g. needles).",
                        de = "Einatmen (Häufigst), Hautaufnahme (Absorption), Verschlucken, Injektion.",
                        pl = "Wdychanie (Najczęstsze), Wchłanianie przez skórę, Połknięcie, Iniekcja."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: BİLGİ KAYNAKLARI (ETİKET VE SDS)
                LessonSection(
                    title = LocalizedText("Bilgi Kaynakları: SDS ve Etiket", "Sources of Info: SDS & Label", "Sicherheitsdatenblatt & Etikett", "Karta Charakterystyki & Etykieta"),
                    content = LocalizedText(
                        tr = "Bir kimyasalın tehlikesini anlamak için 2 kaynağa bakılır:\n\n1. ÜRÜN ETİKETİ: Tehlike sembollerini, risk ibarelerini ve temel önlemleri içerir.\n2. GÜVENLİK BİLGİ FORMU (SDS/MSDS): Üretici tarafından sağlanması ZORUNLUDUR. 16 bölümden oluşur. İçeriğinde; İlk yardım, Yangınla mücadele, Depolama, KKD gereksinimleri ve Toksikolojik bilgiler yer alır.",
                        en = "Key sources:\n1. PRODUCT LABEL: Symbols and basic warnings.\n2. SAFETY DATA SHEET (SDS): Must be provided by supplier. Contains 16 sections (First aid, Fire fighting, Storage, PPE, Toxicology).",
                        de = "Etikett (Symbole) und Sicherheitsdatenblatt (SDS - 16 Abschnitte, Pflicht).",
                        pl = "Etykieta (Symbole) i Karta Charakterystyki (SDS - 16 sekcji, Obowiązkowa)."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 3: MARUZİYET SINIRLARI (WEL/OEL)
                LessonSection(
                    title = LocalizedText("Maruziyet Sınırları (WEL)", "Workplace Exposure Limits (WEL)", "Arbeitsplatzgrenzwerte", "Limity Ekspozycji"),
                    content = LocalizedText(
                        tr = "Havadaki tehlikeli madde konsantrasyonu için yasal sınırlardır. İki türü vardır:\n\n1. LTEL (Uzun Dönem): 8 saatlik çalışma günü içindeki ortalama sınırdır. KRONİK (uzun vadeli) etkilerden korur.\n2. STEL (Kısa Dönem): 15 dakikalık süredeki sınırdır. AKUT (ani) etkilerden korur.\n\nBu sınırlar aşılmamalıdır! Ölçümler genellikle ppm (milyonda kısım) veya mg/m³ ile ifade edilir.",
                        en = "Legal limits for airborne concentrations:\n1. LTEL (Long Term): 8-hour average. Protects from CHRONIC effects.\n2. STEL (Short Term): 15-minute limit. Protects from ACUTE effects.\nUnits: ppm or mg/m³.",
                        de = "LTEL (8 Std. - Chronisch) und STEL (15 Min. - Akut). Grenzwerte dürfen nicht überschritten werden.",
                        pl = "LTEL (8 godz. - Przewlekłe) i STEL (15 min. - Ostre). Limity nie mogą być przekroczone."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 4: SINIRLARIN KISITLAMALARI
                LessonSection(
                    title = LocalizedText("Sınır Değerlerin Kısıtlamaları", "Limitations of Limits", "Grenzen der Grenzwerte", "Ograniczenia Limitów"),
                    content = LocalizedText(
                        tr = "Maruziyet sınırları (WEL) 'Güvenli' ile 'Tehlikeli' arasında kesin bir çizgi değildir. Çünkü:\n\n- Bireysel Farklılıklar: Bazı insanlar daha hassastır veya hastadır.\n- Kokteyl Etkisi: İşyerinde birden fazla kimyasal varsa, bunlar birleşip daha zararlı olabilir (Sinerjik etki).\n- Deri Emilimi: WEL sadece solumayı ölçer, deriden girişi hesaba katmaz.\n- 8 Saatlik Vardiya: Eğer işçiler 12 saat çalışıyorsa hesaplamalar değişmelidir.",
                        en = "WELs are not a sharp line between safe/unsafe because:\n- Individual Variation: Some representives are more sensitive.\n- Cocktail Effect: Mixtures of chemicals can be worse.\n- Skin Absorption: WEL only covers inhalation.\n- Shift Patterns: Based on 8 hours, not 12.",
                        de = "Keine scharfe Linie. Individuelle Unterschiede, Cocktail-Effekt, Hautaufnahme.",
                        pl = "Brak ostrej granicy. Różnice indywidualne, Efekt koktajlowy, Wchłanianie przez skórę."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 7.2
        neboshDao.insertTopic(Topic("7.2", "IG2", "Sağlık Risklerinin Değerlendirilmesi", "Assessment of Health Risks", "Beurteilung von Gesundheitsrisiken", "Ocena Ryzyka Zdrowotnego", Gson().toJson(lesson7_2)))
    }
    // --- ELEMENT 7: KİMYASAL VE BİYOLOJİK AJANLAR ---
    private suspend fun insertElement7Topics() {

        // KONU 7.1: FORMLAR VE SINIFLANDIRMA
        val lesson7_1 = TopicContent(
            sections = listOf(
                // SAYFA 1: KİMYASAL FORMLAR (TANIMLAR ÇOK ÖNEMLİ!)
                LessonSection(
                    title = LocalizedText("Kimyasal Maddelerin Formları", "Forms of Chemical Agents", "Formen chemischer Arbeitsstoffe", "Formy Czynników Chemicznych"),
                    content = LocalizedText(
                        tr = "Kimyasallar fiziksel hallerine göre risk oluşturur. Sınavda tanımlar sorulur:\n\n1. TOZ (Dust): Havadaki katı parçacıklardır (örn. zımparalama).\n2. DUMAN (Fume): Erimiş bir metalin soğuyup yoğunlaşmasıyla oluşan katı parçacıklardır (örn. kaynak dumanı).\n3. GAZ (Gas): Oda sıcaklığında gaz halindedir (örn. Karbonmonoksit).\n4. BUHAR (Vapour): Oda sıcaklığında sıvı olan bir maddenin gaz hali (örn. Benzin buharı).\n5. SİS (Mist): Havadaki sıvı damlacıklarıdır (örn. boya spreyi).",
                        en = "Definitions are key:\n1. DUST: Solid particles (e.g. sanding).\n2. FUME: Condensed solid particles from molten metal (e.g. welding).\n3. GAS: Gas at room temp (e.g. Carbon monoxide).\n4. VAPOUR: Gaseous form of a liquid (e.g. Petrol vapour).\n5. MIST: Liquid droplets (e.g. Paint spray).",
                        de = "Staub (Fest), Rauch (Metall), Gas, Dampf (Flüssigkeit), Nebel (Tropfen).",
                        pl = "Pył (Stały), Dym (Metal), Gaz, Para (Ciecz), Mgła (Krople)."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: BİYOLOJİK AJANLAR
                LessonSection(
                    title = LocalizedText("Biyolojik Ajanlar", "Biological Agents", "Biostoffe", "Czynniki Biologiczne"),
                    content = LocalizedText(
                        tr = "Canlı organizmalardır. 3 ana türe ayrılır:\n\n1. MANTARLAR (Fungi): Küfler ve mayalar (örn. Çiftçi Akciğeri).\n2. BAKTERİLER: Tek hücreli organizmalar (örn. Lejyonella, Tetanos, Leptospirosis).\n3. VİRÜSLER: Sadece canlı hücre içinde çoğalabilirler (örn. Hepatit, HIV/AIDS).\n\nBiyolojik tehlike sembolü: Üç yarım dairenin birleşimi (Biyo-tehlike).",
                        en = "Living organisms:\n1. FUNGI: Moulds/Yeast (e.g. Farmer's Lung).\n2. BACTERIA: Micro-organisms (e.g. Legionella, Tetanus).\n3. VIRUSES: Multiply inside cells (e.g. Hepatitis, HIV).",
                        de = "Pilze (Schimmel), Bakterien (Legionellen), Viren (Hepatitis).",
                        pl = "Grzyby (Pleśń), Bakterie (Legionella), Wirusy (WZW)."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: SAĞLIK ETKİLERİ (AKUT VE KRONİK)
                LessonSection(
                    title = LocalizedText("Akut ve Kronik Etkiler", "Acute vs Chronic Effects", "Akute vs. Chronische Effekte", "Skutki Ostre vs Przewlekłe"),
                    content = LocalizedText(
                        tr = "Kimyasalların etkisi maruziyet süresine bağlıdır:\n\n1. AKUT (Acute): Yüksek doza kısa süreli maruziyet. Etkisi hemen görülür (örn. Asit yanığı, bayılma).\n2. KRONİK (Chronic): Düşük doza uzun süreli (yıllarca) maruziyet. Etkisi yavaş gelişir (örn. Akciğer kanseri, Asbestozis).\n\n3. HASSASİYET (Sensitisation): Bir maddeye zamanla alerji geliştirme (örn. Lateks, izosiyanatlar). Bir kez hassaslaşınca, çok küçük bir miktar bile şiddetli astım krizine neden olabilir.",
                        en = "1. ACUTE: Short term, high dose. Immediate effect (e.g. Burn).\n2. CHRONIC: Long term, low dose. Slow effect (e.g. Cancer).\n3. SENSITISATION: Developing an allergy. Once sensitised, tiny amounts trigger reaction (Asthma).",
                        de = "Akut (Kurz/Hoch), Chronisch (Lang/Niedrig), Sensibilisierung (Allergie).",
                        pl = "Ostre (Krótko/Wysoka), Przewlekłe (Długo/Niska), Uczulenie (Alergia)."
                    ),
                    imageType = "policy"
                ),

                // SAYFA 4: GHS SINIFLANDIRMASI (SEMBOLLER)
                LessonSection(
                    title = LocalizedText("GHS Sınıflandırması", "GHS Classification", "GHS-Klassifizierung", "Klasyfikacja GHS"),
                    content = LocalizedText(
                        tr = "Küresel Uyumlaştırılmış Sistem (GHS) elmas şeklinde kırmızı çerçeveli semboller kullanır:\n\n- Fiziksel: Patlayıcı (Bomba), Yanıcı (Alev), Oksitleyici (Alevli top).\n- Sağlık: Toksik (Kuru kafa), Aşındırıcı (Eriyik el/metal), Sağlık Tehlikesi (Göğsünde yıldız olan adam - Kanserojen/Mutajen).\n- Çevresel: Ölü ağaç ve balık (Çevreye zararlı).\n\nBu sembolleri tanımak sınavda çok önemlidir.",
                        en = "GHS uses diamond symbols:\n- Physical: Explosive, Flammable, Oxidising.\n- Health: Toxic (Skull), Corrosive, Health Hazard (Star on chest - Carcinogen).\n- Environmental: Dead tree/fish.\nRecognising these is vital.",
                        de = "Symbole: Explosiv, Entzündbar, Giftig (Totenkopf), Ätzend, Gesundheitsgefahr.",
                        pl = "Symbole: Wybuchowe, Łatwopalne, Toksyczne (Czaszka), Żrące, Zagrożenie dla zdrowia."
                    ),
                    imageType = "audit"
                )
            )
        )
        // Konu ID'si 7.1
        neboshDao.insertTopic(Topic("7.1", "IG2", "Tehlikeli Maddeler", "Hazardous Substances", "Gefahrstoffe", "Substancje Niebezpieczne", Gson().toJson(lesson7_1)))
    }
    // --- ELEMENT 6.3: YÜK TAŞIMA EKİPMANLARI ---
    private suspend fun insertElement6Topic3() {

        val lesson6_3 = TopicContent(
            sections = listOf(
                // SAYFA 1: TEHLİKELER
                LessonSection(
                    title = LocalizedText("Ekipmanlar ve Tehlikeler", "Equipment & Hazards", "Ausrüstung & Gefahren", "Sprzęt i Zagrożenia"),
                    content = LocalizedText(
                        tr = "Yaygın ekipmanlar: Forkliftler, Vinçler, Konveyörler, Transpaletler.\n\nAna Tehlikeler:\n1. Devrilme: Dengesiz yük, bozuk zemin, aşırı hız veya keskin dönüş.\n2. Yükün Düşmesi: Yanlış bağlama veya kapasite aşımı.\n3. Çarpma: Operatörün yayaları görmemesi.\n4. Elektrik Hattı Teması: Vinç kolunun havai hatlara değmesi (Ölümcül!).\n5. Parça Kopması: Bakımsız ekipman.",
                        en = "Common equipment: Forklifts, Cranes, Conveyors.\nHazards:\n1. Overturning: Unstable load/ground, speed.\n2. Falling Load: Poor securing, overloading.\n3. Impact: Striking pedestrians.\n4. Overhead Lines: Electrocution.\n5. Mechanical Failure.",
                        de = "Gefahren: Umkippen, herabfallende Lasten, Zusammenstöße, Stromleitungen.",
                        pl = "Zagrożenia: Przewrócenie, spadające ładunki, uderzenia, linie energetyczne."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: TEMEL GEREKLİLİKLER (SWL)
                LessonSection(
                    title = LocalizedText("Temel Gereklilikler", "Key Requirements", "Anforderungen", "Wymagania"),
                    content = LocalizedText(
                        tr = "Tüm kaldırma ekipmanları için yasal şartlar:\n\n1. GÜÇ VE DENGE: Ekipman yapılacak işe uygun olmalı.\n2. İŞARETLEME: Güvenli Çalışma Yükü (SWL - Safe Working Load) makinenin üzerinde OKUNAKLI şekilde yazmalı.\n3. KONUMLANDIRMA: Kaymayan, düz bir zemin üzerine kurulmalı.\n4. İNSAN TAŞIMA: Sadece bu iş için tasarlanmışsa (örn. sepetli platform) insan taşınabilir. Forklift çatallarında veya palet üstünde ASLA insan taşınamaz!",
                        en = "Requirements:\n1. STRENGTH & STABILITY: Fit for purpose.\n2. MARKING: Safe Working Load (SWL) must be clearly displayed.\n3. POSITIONING: Firm, level ground.\n4. LIFTING PEOPLE: Only in designed carriers (e.g., MEWP). NEVER on forklift forks!",
                        de = "SWL (Tragfähigkeit) muss gekennzeichnet sein. Personen nur in dafür vorgesehenen Körben heben.",
                        pl = "SWL (Dopuszczalne obciążenie) musi być oznaczone. Ludzie tylko w przeznaczonych do tego koszach."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: KALDIRMA OPERASYONLARI
                LessonSection(
                    title = LocalizedText("Kaldırma Operasyonları", "Lifting Operations", "Hebevorgänge", "Operacje Podnoszenia"),
                    content = LocalizedText(
                        tr = "Her kaldırma işlemi şu 3 kurala uymalıdır:\n\n1. PLANLANMALI: Risk değerlendirmesi yapılmalı (Yük ne? Nereye gidecek?).\n2. GÖZETİLMELİ: Yetkin bir kişi (Slinger/Banksman) operasyonu yönetmeli.\n3. GÜVENLİ YAPILMALI: Yükün altına kimse girmemeli, hava koşulları uygun olmalı.\n\nPeriyodik Muayene: Kaldırma ekipmanları düzenli olarak yetkili mühendislerce kontrol edilmelidir (İnsan taşıyanlar 6 ayda bir, diğerleri 12 ayda bir).",
                        en = "Lifting ops must be:\n1. PLANNED (Risk assessment).\n2. SUPERVISED (Competent person).\n3. CARRIED OUT SAFELY (No one under load).\n\nStatutory Inspections: Every 6 months (lifting people) or 12 months (goods).",
                        de = "Geplant, Überwacht, Sicher durchgeführt. Inspektionen alle 6/12 Monate.",
                        pl = "Zaplanowane, Nadzorowane, Bezpieczne. Inspekcje co 6/12 miesięcy."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 4: FORKLİFT GÜVENLİĞİ
                LessonSection(
                    title = LocalizedText("Forklift Güvenliği", "Forklift Safety", "Gabelstaplersicherheit", "Bezpieczeństwo Wózków"),
                    content = LocalizedText(
                        tr = "Forklift kazalarını önlemek için:\n\n- Eğitim: Operatörler sertifikalı olmalı, anahtarlar yetkisiz kişilere verilmemeli.\n- Bakım: Her vardiya başında günlük kontroller (Lastik, korna, fren, ışık) yapılmalı.\n- Yaya Ayrımı: Forklift yolları ile yaya yolları bariyerlerle ayrılmalı.\n- Çevre: Aynalar, iyi aydınlatma ve hız sınırları uygulanmalı.\n- Şarj/Yakıt: Sadece iyi havalandırılmış, ateşten uzak alanlarda yapılmalı.",
                        en = "Controls:\n- Training: Certified operators only. Keep keys safe.\n- Maintenance: Daily checks (brakes, lights).\n- Segregation: Separate pedestrians and vehicles.\n- Environment: Mirrors, lighting, speed limits.\n- Charging: Well-ventilated areas.",
                        de = "Ausbildung, tägliche Kontrollen, Trennung von Fußgängern, Geschwindigkeitsbegrenzungen.",
                        pl = "Szkolenie, codzienne kontrole, separacja pieszych, limity prędkości."
                    ),
                    imageType = "audit"
                )
            )
        )
        neboshDao.insertTopic(Topic("6.3", "IG2", "Yük Taşıma Ekipmanları", "Load Handling Equipment", "Lastaufnahmemittel", "Sprzęt do Podnoszenia", Gson().toJson(lesson6_3)))
    }
    // --- ELEMENT 6.2: ELLE TAŞIMA (MANUAL HANDLING) ---
    private suspend fun insertElement6Topic2() {

        val lesson6_2 = TopicContent(
            sections = listOf(
                // SAYFA 1: RİSKLER VE YARALANMALAR
                LessonSection(
                    title = LocalizedText("Elle Taşıma Riskleri", "Manual Handling Risks", "Risiken der manuellen Handhabung", "Ryzyko Ręcznego Przenoszenia"),
                    content = LocalizedText(
                        tr = "Elle taşıma; yüklerin elle veya beden gücüyle kaldırılması, taşınması, itilmesi veya çekilmesidir. En yaygın yaralanmalar:\n\n1. Sırt Yaralanmaları: Disk kayması, omurga zedelenmesi, kas spazmları.\n2. Fıtıklar (Hernias): Karın duvarının yırtılması.\n3. Uzuv Yaralanmaları: Kesikler, ezilmeler (yükün düşmesi), kırıklar.\n4. Kas ve Tendon Yaralanmaları: Burkulma ve incinmeler.",
                        en = "Manual handling involves lifting, carrying, pushing, or pulling loads. Common injuries:\n1. Back Injuries: Slipped discs, muscle spasms.\n2. Hernias: Rupture of abdominal wall.\n3. Limb Injuries: Cuts, crushing, fractures.\n4. Muscle/Tendon Injuries: Sprains and strains.",
                        de = "Rückenverletzungen, Leistenbrüche, Gliedmaßenverletzungen, Muskelverletzungen.",
                        pl = "Urazy pleców, przepukliny, urazy kończyn, urazy mięśni."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 2: RİSK DEĞERLENDİRMESİ (TILE KURALI) - ÇOK ÖNEMLİ!
                LessonSection(
                    title = LocalizedText("TILE (LITE) Prensibi", "The TILE Principle", "Das TILE-Prinzip", "Zasada TILE"),
                    content = LocalizedText(
                        tr = "Elle taşıma risk değerlendirmesi yaparken 4 faktöre bakılır (TILE):\n\n1. TASK (Görev): Yükü vücuttan uzakta mı tutuyor? Eğiliyor veya bükülüyor mu? Çok mu sık tekrar ediyor?\n2. INDIVIDUAL (Birey): Fiziksel gücü yetiyor mu? Hamile mi? Eğitimi var mı?\n3. LOAD (Yük): Çok mu ağır? Hacimli mi? Kavraması zor mu? Dengesiz mi? Sıcak/keskin mi?\n4. ENVIRONMENT (Çevre): Zemin kaygan mı? Işık yetersiz mi? Alan dar mı?",
                        en = "Assessment factors (TILE):\n1. TASK: Holding load away from body? Twisting? Repetitive?\n2. INDIVIDUAL: Strong enough? Pregnant? Trained?\n3. LOAD: Heavy? Bulky? Hard to grip? Unstable? Hot/sharp?\n4. ENVIRONMENT: Slippery floor? Poor light? Space constraints?",
                        de = "Aufgabe, Individuum, Last, Umgebung (TILE).",
                        pl = "Zadanie, Jednostka, Ładunek, Środowisko (TILE)."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 3: KONTROL HİYERARŞİSİ
                LessonSection(
                    title = LocalizedText("Kontrol Önlemleri", "Control Measures", "Kontrollmaßnahmen", "Środki Kontroli"),
                    content = LocalizedText(
                        tr = "Riskleri azaltmak için şu sıra izlenmelidir:\n\n1. KAÇIN (Avoid): İşi tamamen ortadan kaldır (Örn: Konveyör bant veya forklift kullanarak otomasyona geç).\n2. DEĞERLENDİR (Assess): Kaçınılamıyorsa TILE prensibine göre risk değerlendirmesi yap.\n3. AZALT (Reduce): Yükü hafiflet (bölerek taşı), mekanik yardımcı kullan (transpalet, el arabası), ekip çalışması yap (iki kişi taşı).",
                        en = "Hierarchy:\n1. AVOID: Automate or mechanise (Conveyors, forklifts).\n2. ASSESS: Risk assessment (TILE).\n3. REDUCE: Split the load, use mechanical aids (trolleys), team lifting.",
                        de = "Vermeiden (Automatisierung), Bewerten, Reduzieren (Hilfsmittel nutzen).",
                        pl = "Unikaj (Automatyzacja), Oceniaj, Redukuj (Użyj wózków)."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 4: DOĞRU KALDIRMA TEKNİĞİ
                LessonSection(
                    title = LocalizedText("Güvenli Kaldırma Tekniği", "Safe Lifting Technique", "Hebetechnik", "Technika Podnoszenia"),
                    content = LocalizedText(
                        tr = "Doğru kaldırma adımları:\n\n1. Planla: Nereye götüreceksin? Yol açık mı?\n2. Ayakları Aç: Dengeli duruş için ayakları omuz genişliğinde aç.\n3. Dizleri Bük: Sırtını değil, bacaklarını kullan.\n4. Sırtı Düz Tut: Kaldırırken omurgayı bükme.\n5. Yakın Tut: Yükü vücuduna mümkün olduğunca yakın tut (Kaldıraç etkisini azaltır).\n6. Ani Hareket Yapma: Yavaş ve kontrollü kaldır.",
                        en = "1. Plan the route.\n2. Feet apart (stable base).\n3. Bend knees (use legs, not back).\n4. Back straight.\n5. Load close to body.\n6. No jerking (smooth movement).",
                        de = "Planen, Füße auseinander, Knie beugen, Rücken gerade, Last nah am Körper.",
                        pl = "Planuj, stopy szeroko, ugnij kolana, plecy proste, ładunek blisko ciała."
                    ),
                    imageType = "hazard"
                )
            )
        )
        // Konu ID'si 6.2
        neboshDao.insertTopic(Topic("6.2", "IG2", "Elle Taşıma", "Manual Handling", "Manuelle Handhabung", "Ręczne Przenoszenie", Gson().toJson(lesson6_2)))
    }
    // --- ELEMENT 6: KAS VE İSKELET SAĞLIĞI ---
    private suspend fun insertElement6Topics() {

        // KONU 6.1: WRULDs VE EKRANLI ARAÇLAR (DSE)
        val lesson6_1 = TopicContent(
            sections = listOf(
                // SAYFA 1: WRULDs NEDİR?
                LessonSection(
                    title = LocalizedText("WRULDs Nedir?", "What are WRULDs?", "Was sind WRULDs?", "Czym są WRULDs?"),
                    content = LocalizedText(
                        tr = "İşle İlgili Üst Uzuv Bozuklukları (WRULDs), vücudun yumuşak dokularını (kaslar, tendonlar, sinirler) etkileyen rahatsızlıklardır. Genellikle parmaklar, eller, kollar ve boyunda görülür.\n\nÖrnekler:\n- Tendinit: Tendon iltihabı.\n- Karpal Tünel Sendromu: Bilekteki sinir sıkışması (Parmaklarda uyuşma).\n- Donuk Omuz: Omuz hareket kısıtlılığı.\n- Kramplar: Yazıcı krampı.",
                        en = "Work-Related Upper Limb Disorders (WRULDs) affect soft tissues (muscles, tendons, nerves) of the fingers, hands, arms, and neck.\n\nExamples:\n- Tendonitis: Inflammation of tendons.\n- Carpal Tunnel Syndrome: Nerve compression in the wrist.\n- Frozen Shoulder.\n- Writer's Cramp.",
                        de = "Arbeitsbedingte Erkrankungen der oberen Gliedmaßen. Sehnenentzündung, Karpaltunnelsyndrom.",
                        pl = "Zaburzenia kończyn górnych związane z pracą. Zapalenie ścięgien, Zespół cieśni nadgarstka."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: RİSK FAKTÖRLERİ
                LessonSection(
                    title = LocalizedText("Risk Faktörleri", "Risk Factors", "Risikofaktoren", "Czynniki Ryzyka"),
                    content = LocalizedText(
                        tr = "WRULDs oluşumuna neden olan ana faktörler:\n\n1. TEKRAR (Repetition): Aynı hareketin sürekli yapılması (Klavye kullanmak, montaj hattı).\n2. KUVVET (Force): Nesneleri sıkıca kavramak veya itmek.\n3. DURUŞ (Posture): Garip veya statik duruşlar (Bileği bükmek, boynu eğmek).\n4. DİNLENME EKSİKLİĞİ: Dokuların iyileşmesine fırsat vermemek.\n5. ÇEVRESEL: Soğuk (kan akışını azaltır) ve titreşim.",
                        en = "Key risk factors:\n1. REPETITION: Frequent same movements.\n2. FORCE: Gripping or pushing.\n3. POSTURE: Awkward or static postures (Bent wrists).\n4. LACK OF REST: No recovery time.\n5. ENVIRONMENT: Cold and vibration.",
                        de = "Wiederholung, Kraft, Haltung, Mangel an Ruhe, Umgebung (Kälte/Vibration).",
                        pl = "Powtarzalność, Siła, Postawa, Brak odpoczynku, Środowisko (Zimno/Wibracje)."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: EKRANLI ARAÇLAR (DSE)
                LessonSection(
                    title = LocalizedText("Ekranlı Araçlar (DSE)", "Display Screen Equipment (DSE)", "Bildschirmgeräte", "Monitory Ekranowe"),
                    content = LocalizedText(
                        tr = "Bilgisayarlar, laptoplar ve akıllı telefonlar DSE olarak adlandırılır. Uzun süreli kullanım şu sorunlara yol açar:\n\n- Kas-İskelet Sorunları: Boyun, sırt ağrısı, WRULDs.\n- Göz Yorgunluğu: Geçici bulanık görme, baş ağrısı (Kalıcı hasar yapmaz!).\n- Stres: İş yükü ve teknoloji stresi.\n\nİşverenler 'İş İstasyonu Değerlendirmesi' (Workstation Assessment) yapmak zorundadır.",
                        en = "DSE includes computers, laptops. Risks:\n- Musculoskeletal: Neck/back pain.\n- Eye Fatigue: Temporary blurring, headaches (Not permanent damage).\n- Stress.\nEmployers must conduct 'Workstation Assessments'.",
                        de = "DSE (Computer) Risiken: Rückenschmerzen, Augenermüdung, Stress. Arbeitsplatzbewertung erforderlich.",
                        pl = "Zagrożenia DSE: Bóle pleców, zmęczenie oczu, stres. Wymagana ocena stanowiska pracy."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 4: İDEAL OTURUŞ POZİSYONU (ERGONOMİ)
                LessonSection(
                    title = LocalizedText("İdeal Duruş (Ergonomi)", "Ideal Posture (Ergonomics)", "Ideale Haltung", "Idealna Postawa"),
                    content = LocalizedText(
                        tr = "Doğru bir DSE kurulumu nasıl olmalı?\n\n- SANDALYE: Bel desteği olmalı, yükseklik ayarlanabilmeli.\n- EKRAN: Göz hizasında veya hemen altında olmalı. Parlamayı önlemek için pencereye dik açıda durmalı.\n- KLAVYE: Bilekler düz durmalı (bükülmemeli), önünde boşluk olmalı.\n- AYAKLAR: Yere düz basmalı (gerekirse ayak desteği).\n- MOLALAR: Sık ve kısa molalar (Ekrandan uzağa bakmak).",
                        en = "Setup:\n- CHAIR: Lumbar support, adjustable.\n- SCREEN: Eye level, no glare.\n- KEYBOARD: Wrists straight, space in front.\n- FEET: Flat on floor.\n- BREAKS: Frequent micro-breaks.",
                        de = "Stuhl (Lordosenstütze), Bildschirm (Augenhöhe), Tastatur (Handgelenke gerade), Füße flach.",
                        pl = "Krzesło (podparcie lędźwi), Ekran (poziom oczu), Klawiatura (nadgarstki proste), Stopy płasko."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 6.1
        neboshDao.insertTopic(Topic("6.1", "IG2", "WRULDs ve Ekranlı Araçlar", "WRULDs & DSE", "WRULDs & Bildschirmgeräte", "WRULDs & DSE", Gson().toJson(lesson6_1)))
    }
    // --- ELEMENT 5.5: MADDE BAĞIMLILIĞI (ALKOL VE UYUŞTURUCU) ---
    private suspend fun insertElement5Topic5() {

        val lesson5_5 = TopicContent(
            sections = listOf(
                // SAYFA 1: KAPSAM NEDİR?
                LessonSection(
                    title = LocalizedText("Kapsam: Sadece Uyuşturucu Değil", "Scope: Not Just Illegal Drugs", "Umfang", "Zakres"),
                    content = LocalizedText(
                        tr = "İşyerinde 'Madde Kötüye Kullanımı' denince akla 4 grup gelmelidir:\n\n1. Alkol: En yaygın sorundur.\n2. Yasadışı Uyuşturucular: Esrar, kokain, eroin vb.\n3. Reçeteli İlaçlar: Yan etkisi uyku yapan veya dikkati dağıtan yasal ilaçlar.\n4. Çözücüler (Solvents): Yapıştırıcı veya boya koklamak (Uçucu maddeler).",
                        en = "Substance misuse covers 4 groups:\n1. Alcohol: Most common.\n2. Illegal Drugs: Cannabis, cocaine, heroin.\n3. Prescription Meds: Legal drugs that cause drowsiness.\n4. Solvents: Glue/paint sniffing.",
                        de = "4 Gruppen: Alkohol, illegale Drogen, verschreibungspflichtige Medikamente, Lösungsmittel.",
                        pl = "4 Grupy: Alkohol, Narkotyki, Leki na receptę, Rozpuszczalniki."
                    ),
                    imageType = "policy"
                ),

                // SAYFA 2: BELİRTİLER (NASIL ANLARIZ?)
                LessonSection(
                    title = LocalizedText("Bağımlılık Belirtileri", "Signs of Misuse", "Anzeichen von Missbrauch", "Oznaki Nadużycia"),
                    content = LocalizedText(
                        tr = "Bir çalışanın madde kullandığını gösteren işaretler:\n\n- Fiziksel: Alkol kokusu, kırmızı gözler, el titremesi, kişisel bakımın azalması.\n- Davranışsal: Ani ruh hali değişimleri (agresiflik), içine kapanma.\n- Performans: Sürekli geç kalma, artan devamsızlık, artan kaza sayısı, iş kalitesinin düşmesi.\n- Hırsızlık: Madde almak için para veya ekipman çalma.",
                        en = "Signs of a worker under influence:\n- Physical: Smell of alcohol, red eyes, shaking.\n- Behavioural: Mood swings, withdrawal.\n- Performance: Lateness, absenteeism, accidents, poor quality.\n- Theft: Stealing to fund habit.",
                        de = "Anzeichen: Geruch, rote Augen, Stimmungsschwankungen, Verspätungen, Diebstahl.",
                        pl = "Objawy: Zapach, czerwone oczy, wahania nastroju, spóźnienia, kradzieże."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: RİSKLER
                LessonSection(
                    title = LocalizedText("İşyeri İçin Riskler", "Risks to Workplace", "Risiken", "Zagrożenia"),
                    content = LocalizedText(
                        tr = "Madde kullanımı algıyı bozar, tepki süresini yavaşlatır ve koordinasyonu azaltır. Özellikle şu işlerde çalışanlar için ölümcüldür:\n\n- Araç ve iş makinesi kullananlar (Sürücüler).\n- Makine operatörleri.\n- Yüksekte çalışanlar.\n- Karar verme yetkisi olanlar (Hatalı karar büyük mali kayba yol açabilir).",
                        en = "Misuse impairs perception, reaction time, and coordination. Deadly for:\n- Drivers.\n- Machine operators.\n- Working at height.\n- Decision makers.",
                        de = "Beeinträchtigung der Wahrnehmung. Risiko für Fahrer und Maschinenbediener.",
                        pl = "Zaburzenia percepcji. Ryzyko dla kierowców i operatorów maszyn."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 4: KONTROL VE POLİTİKA
                LessonSection(
                    title = LocalizedText("Kontrol Politikası", "Control Policy", "Kontrollpolitik", "Polityka Kontroli"),
                    content = LocalizedText(
                        tr = "İşverenin politikası 3 aşamalı olmalıdır:\n\n1. ÖNLEME: Eğitim ve farkındalık yaratma.\n2. TESPİT ETME (Test): İşe girişte, kaza sonrasında veya rastgele testler yapma (Yasal izne bağlıdır).\n3. YÖNETME: İki yaklaşım vardır:\n   - Disiplin: İşten çıkarma (Özellikle güvenlik açısından kritik işlerde).\n   - Destek: Tedavi ve rehabilitasyon imkanı sunma (Hastalık olarak kabul etme).",
                        en = "Policy stages:\n1. PREVENTION: Education.\n2. DETECTION: Testing (Pre-employment, post-accident, random).\n3. MANAGEMENT:\n   - Discipline: Dismissal.\n   - Support: Treatment/Rehab.",
                        de = "Prävention, Erkennung (Tests), Management (Disziplin oder Unterstützung).",
                        pl = "Prewencja, Wykrywanie (Testy), Zarządzanie (Dyscyplina lub Wsparcie)."
                    ),
                    imageType = "audit"
                )
            )
        )
        neboshDao.insertTopic(Topic("5.5", "IG2", "Madde Bağımlılığı", "Substance Abuse", "Drogenmissbrauch", "Nadużywanie Substancji", Gson().toJson(lesson5_5)))
    }
    // --- ELEMENT 5.4: AKIL SAĞLIĞI VE STRES ---
    private suspend fun insertElement5Topic4() {

        val lesson5_4 = TopicContent(
            sections = listOf(
                // SAYFA 1: STRES NEDİR?
                LessonSection(
                    title = LocalizedText("Stres Nedir?", "What is Stress?", "Was ist Stress?", "Czym jest stres?"),
                    content = LocalizedText(
                        tr = "Stres, bir hastalık değildir. 'İnsanların üzerlerine yüklenen aşırı baskı veya taleplere karşı verdikleri olumsuz tepkidir'.\n\nKısa süreli baskı motive edici olabilir (örn. sınav stresi), ancak uzun süreli stres fiziksel ve zihinsel hastalıklara (Depresyon, Anksiyete, Kalp hastalıkları) yol açar.",
                        en = "Stress is not a disease. It is 'the adverse reaction people have to excessive pressures or other types of demand placed on them'.\n\nShort-term pressure can be motivating, but prolonged stress leads to illness (Depression, Anxiety, Heart disease).",
                        de = "Stress ist keine Krankheit, sondern eine Reaktion auf übermäßigen Druck. Führt zu Depressionen und Herzerkrankungen.",
                        pl = "Stres to nie choroba, ale reakcja na nadmierną presję. Prowadzi do depresji i chorób serca."
                    ),
                    imageType = "policy"
                ),

                // SAYFA 2: STRESİN 6 NEDENİ (HSE STANDARTLARI)
                LessonSection(
                    title = LocalizedText("Stresin Nedenleri (6 Kural)", "Causes of Stress (HSE 6)", "Stressursachen", "Przyczyny Stresu"),
                    content = LocalizedText(
                        tr = "İngiltere HSE kurumuna göre işyeri stresinin 6 ana kaynağı vardır:\n\n1. TALEPLER (Demands): Aşırı iş yükü, uzun saatler.\n2. KONTROL (Control): İşçinin işi üzerinde söz hakkı olmaması.\n3. DESTEK (Support): Yöneticilerden veya arkadaşlardan destek görememek.\n4. İLİŞKİLER (Relationships): Zorbalık (Bullying), taciz veya çatışma.\n5. ROL (Role): Ne yapacağını bilememek (Rol belirsizliği).\n6. DEĞİŞİM (Change): Değişimin kötü yönetilmesi ve belirsizlik.",
                        en = "HSE Management Standards (6 Causes):\n1. DEMANDS: Workload, hours.\n2. CONTROL: Lack of autonomy.\n3. SUPPORT: Lack of help.\n4. RELATIONSHIPS: Bullying, conflict.\n5. ROLE: Role ambiguity.\n6. CHANGE: Poorly managed change.",
                        de = "6 Ursachen: Anforderungen, Kontrolle, Unterstützung, Beziehungen, Rolle, Veränderung.",
                        pl = "6 Przyczyn: Wymagania, Kontrola, Wsparcie, Relacje, Rola, Zmiana."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 3: ETKİLERİ VE BELİRTİLERİ
                LessonSection(
                    title = LocalizedText("Stresin Etkileri", "Effects of Stress", "Auswirkungen von Stress", "Skutki Stresu"),
                    content = LocalizedText(
                        tr = "Stresin etkileri ikiye ayrılır:\n\n1. BİREY ÜZERİNDE: Uyku bozukluğu, sinirlilik, alkol/sigara kullanımının artması, depresyon, kalp çarpıntısı.\n2. ÖRGÜT ÜZERİNDE: Yüksek personel devir hızı (işten ayrılmalar), artan devamsızlık, düşük üretim kalitesi, artan kaza sayısı.",
                        en = "1. ON INDIVIDUAL: Sleep problems, irritability, alcohol/drug use, depression.\n2. ON ORGANISATION: High staff turnover, absenteeism, poor quality, increased accidents.",
                        de = "Individuum: Schlafstörungen, Alkohol. Organisation: Hohe Fluktuation, Fehlzeiten.",
                        pl = "Jednostka: Problemy ze snem, alkohol. Organizacja: Rotacja pracowników, absencja."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 4: TRAVMA SONRASI STRES BOZUKLUĞU (PTSD)
                LessonSection(
                    title = LocalizedText("Travma Sonrası Stres (PTSD)", "PTSD", "PTBS", "PTSD"),
                    content = LocalizedText(
                        tr = "PTSD, şok edici, korkutucu veya tehlikeli bir olaya tanık olduktan veya yaşadıktan sonra gelişen bir durumdur (örn. ciddi bir iş kazası, patlama, saldırı).\n\nBelirtiler:\n- Olayı tekrar yaşama (Flashback).\n- Kabuslar.\n- Olayı hatırlatan yerlerden kaçınma.\n- Aşırı tetikte olma hali.",
                        en = "PTSD develops after experiencing a shocking, scary, or dangerous event (e.g., serious accident, explosion, assault).\nSymptoms: Flashbacks, nightmares, avoidance, anxiety.",
                        de = "Posttraumatische Belastungsstörung (PTBS) nach Schockereignissen. Flashbacks, Albträume.",
                        pl = "Zespół stresu pourazowego (PTSD) po szoku. Retrospekcje, koszmary."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 5: İŞYERİNDE ŞİDDET
                LessonSection(
                    title = LocalizedText("İşyerinde Şiddet", "Violence at Work", "Gewalt am Arbeitsplatz", "Przemoc w Pracy"),
                    content = LocalizedText(
                        tr = "İşyeri şiddeti sadece fiziksel saldırı değildir. 'Bir kişinin işiyle ilgili koşullarda istismar edilmesi, tehdit edilmesi veya saldırıya uğramasıdır'.\n\nKimler Risk Altında?\n- Para taşıyanlar (Banka, kasiyer).\n- Halkla yüz yüze çalışanlar (Sağlıkçılar, polis, sosyal hizmetler).\n- Yalnız çalışanlar.\n\nKontrol: Nakit parayı azaltmak, cam bölmeler, CCTV, panik butonları, eğitim.",
                        en = "Violence is 'Any incident where a person is abused, threatened or assaulted in circumstances relating to their work'.\nRisks: Handling cash, dealing with public, lone working.\nControls: Reduce cash, screens, CCTV, panic buttons, training.",
                        de = "Gewalt umfasst Missbrauch, Drohungen und Angriffe. Risiken: Bargeld, Öffentlichkeit.",
                        pl = "Przemoc to nadużycia, groźby i ataki. Ryzyko: Gotówka, kontakt z publicznością."
                    ),
                    imageType = "pdca"
                )
            )
        )
        neboshDao.insertTopic(Topic("5.4", "IG2", "Akıl Sağlığı ve Stres", "Mental Health & Stress", "Psychische Gesundheit", "Zdrowie Psychiczne", Gson().toJson(lesson5_4)))
    }
    // --- ELEMENT 5.3: RADYASYON ---
    private suspend fun insertElement5Topic3() {

        val lesson5_3 = TopicContent(
            sections = listOf(
                // SAYFA 1: RADYASYON TÜRLERİ
                LessonSection(
                    title = LocalizedText("Radyasyon Türleri", "Types of Radiation", "Strahlungsarten", "Rodzaje Promieniowania"),
                    content = LocalizedText(
                        tr = "Radyasyon iki ana türe ayrılır:\n\n1. İYONLAŞTIRICI (Ionising): Atomlardan elektron koparacak kadar yüksek enerjiye sahiptir. Hücre yapısını ve DNA'yı bozar. (Örn: X-Işınları, Alfa, Beta, Gama ışınları).\n\n2. İYONLAŞTIRICI OLMAYAN (Non-ionising): Daha düşük enerjilidir ancak yine de zarar verebilir (Isı ve yanık). (Örn: UV ışınları, Lazerler, Mikrodalga, Radyo dalgaları).",
                        en = "Radiation is split into two types:\n1. IONISING: High energy, strips electrons from atoms. Damages DNA. (e.g., X-Rays, Alpha, Beta, Gamma).\n2. NON-IONISING: Lower energy but can still cause harm (heat/burns). (e.g., UV, Lasers, Microwave, Radio waves).",
                        de = "Ionisierend (Röntgen, Alpha, Beta, Gamma - DNA-Schäden) vs. Nicht-ionisierend (UV, Laser - Verbrennungen).",
                        pl = "Jonizujące (Rentgen, Alfa, Beta, Gamma - uszkodzenia DNA) vs. Niejonizujące (UV, Lasery - oparzenia)."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: SAĞLIK ETKİLERİ
                LessonSection(
                    title = LocalizedText("Sağlık Etkileri", "Health Effects", "Gesundheitliche Auswirkungen", "Skutki Zdrowotne"),
                    content = LocalizedText(
                        tr = "İYONLAŞTIRICI:\n- Akut: Radyasyon hastalığı (kusma, saç dökülmesi), yanıklar.\n- Kronik: Kanser (Lösemi), genetik mutasyonlar (doğmamış bebeklerde sakatlık).\n\nİYONLAŞTIRICI OLMAYAN:\n- UV (Güneş/Kaynak): Cilt kanseri (Melanoma), 'Kaynakçı Gözü' (Arc eye).\n- Lazer: Gözde kalıcı körlük ve cilt yanıkları.\n- Mikrodalga: İç organların ısınması.",
                        en = "IONISING:\n- Acute: Radiation sickness, burns.\n- Chronic: Cancer (Leukaemia), genetic mutations.\n\nNON-IONISING:\n- UV: Skin cancer, 'Arc eye'.\n- Lasers: Blindness, burns.\n- Microwaves: Internal heating.",
                        de = "Ionisierend: Krebs, Strahlenkrankheit. Nicht-ionisierend: Hautkrebs (UV), Blindheit (Laser).",
                        pl = "Jonizujące: Rak, choroba popromienna. Niejonizujące: Rak skóry (UV), ślepota (Laser)."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: KORUNMA YÖNTEMLERİ (3 TEMEL KURAL)
                LessonSection(
                    title = LocalizedText("Korunma: Zaman, Mesafe, Zırhlama", "Protection: Time, Distance, Shielding", "Schutz: Zeit, Abstand, Abschirmung", "Ochrona: Czas, Odległość, Osłona"),
                    content = LocalizedText(
                        tr = "İyonlaştırıcı radyasyondan korunmanın 3 altın kuralı vardır:\n\n1. ZAMAN (Time): Maruziyet süresini mümkün olduğunca kısa tutun.\n2. MESAFE (Distance): Kaynaktan uzak durun (Radyasyonun şiddeti mesafe ile karesel olarak azalır).\n3. ZIRHLAMA (Shielding): Araya bariyer koyun (Örn: Kurşun önlük, beton duvar).",
                        en = "The 3 golden rules of protection:\n1. TIME: Minimize exposure duration.\n2. DISTANCE: Keep away from the source.\n3. SHIELDING: Use barriers (Lead aprons, concrete walls).",
                        de = "3 Goldene Regeln: Zeit minimieren, Abstand halten, Abschirmung (Blei/Beton).",
                        pl = "3 złote zasady: Minimalizuj czas, Zachowaj dystans, Osłona (Ołów/Beton)."
                    ),
                    imageType = "pdca"
                ),

                // SAYFA 4: RADON GAZI
                LessonSection(
                    title = LocalizedText("Radon Gazı", "Radon Gas", "Radongas", "Gaz Radon"),
                    content = LocalizedText(
                        tr = "Radon, topraktaki uranyumun bozunmasıyla oluşan doğal, kokusuz ve renksiz bir radyoaktif gazdır.\n\n- Binaların altından (zemin çatlaklarından) içeri sızabilir.\n- Akciğer kanserinin sigaradan sonraki en büyük ikinci nedenidir.\n- Kontrolü: Zemin yalıtımı yapmak, bina altındaki boşluğu havalandırmak (pozitif basınçlandırma).",
                        en = "Radon is a natural, odourless radioactive gas from uranium in the ground.\n- Seeps into buildings through cracks.\n- 2nd leading cause of lung cancer.\n- Control: Seal floors, improve under-floor ventilation (sumps).",
                        de = "Natürliches radioaktives Gas aus dem Boden. Lungenkrebsrisiko. Kontrolle: Bodenabdichtung, Lüftung.",
                        pl = "Naturalny gaz promieniotwórczy z ziemi. Ryzyko raka płuc. Kontrola: Uszczelnianie podłóg, wentylacja."
                    ),
                    imageType = "audit"
                )
            )
        )
        neboshDao.insertTopic(Topic("5.3", "IG2", "Radyasyon", "Radiation", "Strahlung", "Promieniowanie", Gson().toJson(lesson5_3)))
    }
    // --- ELEMENT 5.2: TİTREŞİM (VIBRATION) ---
    private suspend fun insertElement5Topic2() {

        val lesson5_2 = TopicContent(
            sections = listOf(
                // SAYFA 1: TİTREŞİM TÜRLERİ VE ETKİLERİ
                LessonSection(
                    title = LocalizedText("Titreşim Türleri", "Types of Vibration", "Arten von Vibrationen", "Rodzaje Wibracji"),
                    content = LocalizedText(
                        tr = "İşyerinde titreşim iki ana yolla vücuda girer:\n\n1. EL-KOL TİTREŞİMİ (HAV - Hand-Arm Vibration): Motorlu el aletlerinden (matkap, taşlama, motorlu testere) ellere ve kollara geçen titreşimdir.\n2. TÜM VÜCUT TİTREŞİMİ (WBV - Whole-Body Vibration): Genellikle oturduğumuz veya ayakta durduğumuz yüzeyden gelir. Büyük iş makineleri (kamyon, traktör, ekskavatör) kullananlarda görülür ve şiddetli sırt/omurga ağrılarına neden olur.",
                        en = "Vibration enters the body in two main ways:\n1. HAND-ARM VIBRATION (HAV): From hand-held power tools (drills, chainsaws).\n2. WHOLE-BODY VIBRATION (WBV): Transmitted through the seat or feet. Common in heavy machinery drivers (tractors, excavators). Causes severe back pain.",
                        de = "Hand-Arm-Vibration (HAV) durch Werkzeuge und Ganzkörpervibration (WBV) durch Maschinen/Fahrzeuge (verursacht Rückenschmerzen).",
                        pl = "Wibracje miejscowe (HAV) od narzędzi ręcznych i wibracje ogólne (WBV) od maszyn/pojazdów (powodują bóle pleców)."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: EL-KOL TİTREŞİM SENDROMU (HAVS)
                LessonSection(
                    title = LocalizedText("El-Kol Titreşim Sendromu (HAVS)", "HAVS", "HAVS", "Zespół Wibracyjny (HAVS)"),
                    content = LocalizedText(
                        tr = "El-kol titreşimine uzun süre maruz kalmak geri döndürülemez bir hastalık olan HAVS'a neden olur. Belirtileri şunlardır:\n\n- Titreşime Bağlı Beyaz Parmak (VWF): Kan damarlarının hasar görmesi sonucu parmakların beyazlaması ve soğukta ağrıması.\n- Sinir Hasarı: Parmaklarda uyuşma ve karıncalanma (düğme iliklemek gibi ince işleri yapamama).\n- Eklem Hasarı: Kemik ve eklemlerde güç kaybı.",
                        en = "Long-term exposure to HAV causes an irreversible condition called HAVS. Symptoms include:\n- Vibration White Finger (VWF): Blanching of fingers due to blood vessel damage. Painful in cold.\n- Nerve Damage: Numbness and tingling (loss of manual dexterity).\n- Joint Damage: Loss of grip strength.",
                        de = "HAVS verursacht Weißfingerkrankheit (VWF), Nervenschäden (Taubheit) und Gelenkschäden. Es ist irreversibel.",
                        pl = "HAVS powoduje chorobę białych palców (VWF), uszkodzenie nerwów (drętwienie) i uszkodzenie stawów. Jest to proces nieodwracalny."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 3: DEĞERLENDİRME VE SINIRLAR
                LessonSection(
                    title = LocalizedText("Maruziyet Sınırları", "Exposure Limits", "Expositionsgrenzwerte", "Limity Ekspozycji"),
                    content = LocalizedText(
                        tr = "Titreşim ivme ile ölçülür (m/s²). Tıpkı gürültüde olduğu gibi yasal sınırlar vardır:\n\n1. Eylem Değeri (EAV): Bu değere ulaşılırsa işveren riski azaltmak için teknik/organizasyonel önlemler almalı ve sağlık gözetimi başlatmalıdır.\n2. Sınır Değer (ELV): İşçinin kesinlikle maruz kalmaması gereken maksimum yasal sınırdır.\n\nRisk faktörleri: Ekipmanın ne kadar titreştiği, ne kadar süre kullanıldığı ve işçinin çalışma duruşudur.",
                        en = "Measured in acceleration (m/s²). There are legal limits:\n1. Exposure Action Value (EAV): Employer must take action to reduce risk and start health surveillance.\n2. Exposure Limit Value (ELV): Absolute maximum limit that must not be exceeded.\nRisk factors: Vibration magnitude, duration of exposure, and posture.",
                        de = "Gemessen in m/s². Es gibt Auslösewerte (EAV) für Maßnahmen und absolute Grenzwerte (ELV).",
                        pl = "Mierzone w m/s². Istnieją wartości progowe (EAV) dla działań oraz wartości graniczne (ELV)."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 4: TİTREŞİM KONTROL YÖNTEMLERİ
                LessonSection(
                    title = LocalizedText("Titreşim Kontrolü", "Vibration Control", "Vibrationskontrolle", "Kontrola Wibracji"),
                    content = LocalizedText(
                        tr = "Titreşimi önlemek için hiyerarşik kontroller:\n\n- Eliminasyon: İşi tamamen makineleştirin veya farklı bir yöntem kullanın (örn. kırıcı yerine hidrolik ezici kullanmak).\n- Ekipman Seçimi: Titreşimi düşük (ergonomik) aletler satın alın.\n- Bakım: Aşınmış parçalar ve kör uçlar aletin daha fazla titremesine neden olur. Düzenli bakım yapın.\n- Süreyi Kısıtla: İş rotasyonu ile maruziyet süresini kısaltın.\n- Sıcak Tutun: Elleri sıcak tutmak (eldiven) kan dolaşımını artırır ve VWF riskini azaltır.",
                        en = "Hierarchy of controls:\n- Eliminate: Automate or change method (e.g., use hydraulic crusher instead of breaker).\n- Selection: Buy low-vibration tools.\n- Maintenance: Worn parts/blunt bits vibrate more. Maintain them.\n- Limit Time: Job rotation.\n- Keep Warm: Warm hands improve blood flow, reducing VWF risk.",
                        de = "Beseitigen (Automatisierung), Auswahl (vibrationsarm), Wartung, Zeitbegrenzung, Hände warm halten.",
                        pl = "Eliminacja (Automatyzacja), Wybór (niski poziom wibracji), Konserwacja, Ograniczenie czasu, Utrzymywanie ciepła rąk."
                    ),
                    imageType = "pdca"
                )
            )
        )
        neboshDao.insertTopic(Topic("5.2", "IG2", "Titreşim", "Vibration", "Vibration", "Wibracje", Gson().toJson(lesson5_2)))
    }
    // --- ELEMENT 5: FİZİKSEL VE PSİKOLOJİK SAĞLIK ---
    private suspend fun insertElement5Topics() {

        // KONU 5.1: GÜRÜLTÜ (NOISE)
        val lesson5_1 = TopicContent(
            sections = listOf(
                // SAYFA 1: GÜRÜLTÜNÜN ETKİLERİ
                LessonSection(
                    title = LocalizedText("Gürültünün Sağlığa Etkileri", "Health Effects of Noise", "Gesundheitliche Auswirkungen von Lärm", "Skutki Hałasu"),
                    content = LocalizedText(
                        tr = "Gürültü 'istenmeyen ses'tir. Etkileri ikiye ayrılır:\n\n1. AKUT (Ani) ETKİLER:\n- Geçici Eşik Kayması (Temporary Threshold Shift): Gürültülü bir konserden sonra kulakların uğuldaması. Geçicidir.\n- Akustik Travma: Patlama gibi çok yüksek sese maruz kalma. Kulak zarı yırtılabilir.\n\n2. KRONİK (Uzun Vadeli) ETKİLER:\n- Kalıcı Eşik Kayması (NIHL): Tüy hücrelerinin ölmesidir. Geri dönüşü YOKTUR.\n- Tinnitus (Çınlama): Kulakta sürekli çınlama sesi.\n- Presbikusis: Yaşlılığa bağlı işitme kaybı.",
                        en = "Noise is 'unwanted sound'.\n1. ACUTE: Temporary Threshold Shift (ringing ears), Acoustic Trauma (explosion).\n2. CHRONIC: Noise-Induced Hearing Loss (NIHL - Permanent), Tinnitus (Ringing), Presbycusis (Age-related).",
                        de = "Akut (Temporäre Verschiebung, Trauma) vs. Chronisch (Dauerhafter Verlust, Tinnitus).",
                        pl = "Ostre (Tymczasowe przesunięcie, Uraz) vs. Przewlekłe (Trwała utrata, Szum w uszach)."
                    ),
                    imageType = "hazard"
                ),

                // SAYFA 2: TEMEL TERİMLER VE ÖLÇÜM
                LessonSection(
                    title = LocalizedText("Desibel ve Ölçüm", "Decibels & Measurement", "Dezibel & Messung", "Decybele i Pomiary"),
                    content = LocalizedText(
                        tr = "Sesi tanımlamak için iki terim kullanılır:\n\n1. Frekans (Hz): Sesin perdesi (tiz/bas).\n2. Şiddet (dB): Sesin yüksekliği/basıncı.\n\nÖlçüm yaparken insan kulağını taklit eden 'A-Ağırlıklı' skala (dB(A)) kullanılır. Ani patlamalar için ise 'C-Ağırlıklı' (dB(C)) kullanılır.\n\nÖnemli Kural: 3dB Kuralı. Ses seviyesi her 3dB arttığında, ses enerjesi İKİ KATINA çıkar. (83dB, 80dB'den iki kat daha gürültülüdür!)",
                        en = "1. Frequency (Hz): Pitch.\n2. Intensity (dB): Loudness.\nWe use 'A-Weighting' dB(A) to mimic the human ear.\n\n3dB Rule: Every 3dB increase DOUBLES the sound energy! 83dB is twice as loud as 80dB.",
                        de = "Frequenz (Hz) und Intensität (dB). dB(A) für das menschliche Ohr. 3dB-Regel: Energieverdopplung.",
                        pl = "Częstotliwość (Hz) i Intensywność (dB). dB(A) dla ucha ludzkiego. Zasada 3dB: Podwojenie energii."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 3: MARUZİYET SINIRLARI (ÇOK ÖNEMLİ!)
                LessonSection(
                    title = LocalizedText("Maruziyet Eylem Değerleri", "Exposure Action Values", "Auslösewerte", "Wartości Progu Działania"),
                    content = LocalizedText(
                        tr = "Yasa (Avrupa/İngiltere), günlük (8 saatlik) ortalama maruziyet için 3 seviye belirler:\n\n1. Alt Eylem Değeri: 80 dB(A). İşveren kulak koruyucuyu hazır bulundurmalıdır (İşçi isterse takar).\n2. Üst Eylem Değeri: 85 dB(A). İşveren kulak koruyucuyu ZORUNLU kılmalı ve gürültüyü azaltmak için teknik önlem almalıdır.\n3. Maruziyet Sınır Değeri: 87 dB(A). Kulak koruyucu takılıyken bile aşılamayacak mutlak sınırdır.",
                        en = "Legal limits (Daily/8hr):\n1. Lower Action: 80 dB(A) - PPE available.\n2. Upper Action: 85 dB(A) - PPE mandatory & noise reduction zones.\n3. Limit Value: 87 dB(A) - Absolute limit (taking PPE into account).",
                        de = "Untere (80dB), Obere (85dB) Auslösewerte und Grenzwert (87dB).",
                        pl = "Dolne (80dB), Górne (85dB) wartości progu działania i Wartość graniczna (87dB)."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 4: GÜRÜLTÜ KONTROLÜ
                LessonSection(
                    title = LocalizedText("Gürültü Kontrol Yöntemleri", "Noise Control Methods", "Lärmschutzmaßnahmen", "Metody Kontroli Hałasu"),
                    content = LocalizedText(
                        tr = "Kontrol hiyerarşisi uygulanmalıdır:\n\n1. KAYNAKTA (Source): Daha sessiz makine al, bakımı yap (yağla), susturucu tak (Silencing), sönümleme yap (Damping - metalin çınlamasını önle).\n2. YOLDA (Path): Makineyi izole et (Enclosure), ses emici bariyerler veya duvarlar kullan (Insulation).\n3. ALICIDA (Receiver): İşitme koruması (Kulaklık/Tıkaç) ve maruziyet süresini kısıtlamak (Limiting time).",
                        en = "Hierarchy:\n1. SOURCE: Silencing, Damping, Maintenance.\n2. PATH: Enclosure, Insulation, Barriers.\n3. RECEIVER: Hearing protection (Ear muffs/plugs), Limiting exposure time.",
                        de = "Quelle (Schalldämpfung), Weg (Isolierung), Empfänger (Gehörschutz).",
                        pl = "Źródło (Wyciszanie), Droga (Izolacja), Odbiorca (Ochrona słuchu)."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 5.1 (Element 5'in ilk konusu)
        neboshDao.insertTopic(Topic("5.1", "IG2", "Gürültü", "Noise", "Lärm", "Hałas", Gson().toJson(lesson5_1)))
    }// --- ELEMENT 4.4: PERFORMANS GÖZDEN GEÇİRME ---
    private suspend fun insertElement4Topic4() {

        val lesson4_4 = TopicContent(
            sections = listOf(
                // SAYFA 1: GÖZDEN GEÇİRMENİN AMACI
                LessonSection(
                    title = LocalizedText("Gözden Geçirmenin Amacı", "Purpose of Review", "Zweck der Überprüfung", "Cel Przeglądu"),
                    content = LocalizedText(
                        tr = "Gözden geçirme, PUKÖ döngüsünün 'Önlem Al' (Act) aşamasıdır. Yönetim düzenli aralıklarla (genellikle yıllık) oturup şu soruları sormalıdır:\n\n1. Hedeflerimize ulaştık mı?\n2. Ulaşamadıysak neden?\n3. Neyi değiştirmemiz gerekiyor?\n\nAmaç sadece geçmişe bakmak değil, gelecekte 'Sürekli İyileştirme' (Continuous Improvement) sağlamaktır.",
                        en = "Review is the 'Act' stage of PDCA. Management must ask:\n1. Did we hit our targets?\n2. If not, why?\n3. What do we need to change?\nThe goal is 'Continuous Improvement'.",
                        de = "Zweck: Haben wir unsere Ziele erreicht? Wenn nicht, warum? Fokus auf kontinuierliche Verbesserung.",
                        pl = "Cel: Czy osiągnęliśmy cele? Jeśli nie, dlaczego? Ciągłe doskonalenie."
                    ),
                    imageType = "policy"
                ),

                // SAYFA 2: KİM VE NE ZAMAN?
                LessonSection(
                    title = LocalizedText("Kim ve Ne Zaman?", "Who and When?", "Wer und Wann?", "Kto i Kiedy?"),
                    content = LocalizedText(
                        tr = "Gözden geçirme her seviyede yapılmalıdır:\n\n- ÜST YÖNETİM (Yönetim Kurulu): Yılda bir kez. Stratejik hedefleri ve genel performansı değerlendirir.\n- ORTA KADEME YÖNETİM: Üç ayda bir. Departman hedeflerini ve trendleri inceler.\n- BÖLÜM YÖNETİCİLERİ: Ayda bir. Yerel sorunları (teftişler, kazalar) inceler.\n\nBilgi akışı aşağıdan yukarıya doğru olmalıdır.",
                        en = "Reviews happen at all levels:\n- BOARD: Annually (Strategic).\n- MIDDLE MANAGEMENT: Quarterly.\n- DEPARTMENT MANAGERS: Monthly (Local issues).\nInformation flows bottom-up.",
                        de = "Vorstand (Jährlich), Management (Vierteljährlich), Abteilungsleiter (Monatlich).",
                        pl = "Zarząd (Rocznie), Kierownictwo (Kwartalnie), Kierownicy Działów (Miesięcznie)."
                    ),
                    imageType = "audit"
                ),

                // SAYFA 3: GİRDİLER (NE İNCELENİR?)
                LessonSection(
                    title = LocalizedText("Gözden Geçirme Girdileri", "Inputs to Review", "Eingaben zur Überprüfung", "Dane do Przeglądu"),
                    content = LocalizedText(
                        tr = "Karar vermek için veriye ihtiyacımız vardır. Şunlar incelenir:\n\n- İstatistikler: Kaza oranları, hastalık verileri, devamsızlık.\n- Denetim Sonuçları: Sistem çalışıyor mu?\n- Yasal Uyum: Yeni kanunlar çıktı mı?\n- Kaynaklar: Yeterli bütçe ve personel var mı?\n- Hedefler: Geçen seneki hedefler tuttu mu?",
                        en = "What do we look at?\n- Stats: Accidents, ill-health, absence.\n- Audit Reports: Is the system working?\n- Legal Compliance: New laws?\n- Resources: Enough budget/staff?\n- Targets: Did we meet last year's goals?",
                        de = "Statistiken, Auditberichte, Einhaltung von Gesetzen, Ressourcen, Ziele.",
                        pl = "Statystyki, Raporty z audytów, Zgodność z prawem, Zasoby, Cele."
                    ),
                    imageType = "warning"
                ),

                // SAYFA 4: ÇIKTILAR (SONUÇ)
                LessonSection(
                    title = LocalizedText("Gözden Geçirme Çıktıları", "Outputs from Review", "Ergebnisse der Überprüfung", "Wyniki Przeglądu"),
                    content = LocalizedText(
                        tr = "Toplantı bittiğinde elimizde şunlar olmalıdır:\n\n1. YENİ HEDEFLER: Gelecek yıl için (örn. 'Kazaları %10 azaltmak').\n2. EYLEM PLANLARI: Hedeflere ulaşmak için kim, ne yapacak?\n3. YILLIK RAPOR: Paydaşlara (hissedarlar, çalışanlar) performansın duyurulması.\n4. KAYNAKLAR: Gerekirse ek bütçe veya personel onayı.",
                        en = "After the meeting, we need:\n1. NEW TARGETS: For next year.\n2. ACTION PLANS: Who does what?\n3. ANNUAL REPORT: Inform stakeholders.\n4. RESOURCES: Budget approval.",
                        de = "Neue Ziele, Aktionspläne, Jahresbericht, Ressourcen.",
                        pl = "Nowe cele, Plany działań, Raport roczny, Zasoby."
                    ),
                    imageType = "pdca"
                )
            )
        )
        // Konu ID'si 4.4
        neboshDao.insertTopic(Topic("4.4", "IG1", "Performansın Gözden Geçirilmesi", "Performance Review", "Leistungsüberprüfung", "Przegląd Wyników", Gson().toJson(lesson4_4)))
    }
    // 2. DERS İÇERİK FONKSİYONLARI (Alt Alta)
    // --- ELEMENT 1 ---
    private suspend fun insertElement1Topics() {
        val lesson1_1 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Giriş ve Temel Tanımlar", "Intro & Key Definitions", "Einführung & Definitionen", "Wstęp i Definicje"),
                    content = LocalizedText(
                        tr = "Bir kurumun İSG'yi yönetmesinin 3 ana nedeni vardır: Ahlaki, Yasal ve Finansal. Önce temel terimleri anlayalım:\n\n- SAĞLIK (Health): Hastalık veya sağlıkta bozulmanın olmamasıdır. Sadece fiziksel değil, psikolojik sağlığı da (örn. stres) kapsar.\n- GÜVENLİK (Safety): Ciddi kişisel yaralanma riskinin olmamasıdır (örn. yük altında yürümemek).\n- REFAH (Welfare): Temel tesislere erişimdir (Tuvalet, içme suyu, dinlenme odaları, hijyenik yemek alanları).",
                        en = "There are 3 reasons to manage H&S: Moral, Legal, Financial. Key terms:\n\n- HEALTH: Absence of disease/ill-health (Physical & Psychological).\n- SAFETY: Absence of risk of serious injury.\n- WELFARE: Access to basic facilities (Toilets, water, rest areas).",
                        de = "3 Gründe: Moralisch, Rechtlich, Finanziell. Definitionen: Gesundheit, Sicherheit, Wohlergehen.",
                        pl = "3 powody: Moralne, Prawne, Finansowe. Definicje: Zdrowie, Bezpieczeństwo, Dobrostan."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("1. Ahlaki Neden", "1. The Moral Reason", "1. Der moralische Grund", "1. Powód moralny"),
                    content = LocalizedText(
                        tr = "Ahlaki neden, 'yapılacak doğru şey'dir. Toplum, insanların işe gittikleri gibi sağlıklı dönmelerini bekler (Özen Gösterme Borcu).\n\nILO İstatistikleri sorunun boyutunu gösterir:\n- Yılda 2.75 milyondan fazla ölüm (2.4 milyonu meslek hastalığı).\n- Yılda 270 milyondan fazla iş kazası.\n- Yılda 350.000'den fazla ölümcül kaza.\n\nBu rakamların arkasında yıkılan aileler vardır. Bu durum ahlaki olarak kabul edilemez.",
                        en = "The moral reason is 'the right thing to do'. Duty of Care.\n\nILO Statistics:\n- 2.75m+ deaths/year (2.4m from disease).\n- 270m+ accidents/year.\n- 350,000+ fatal accidents.\n\nBehind these numbers are devastated families.",
                        de = "Moralische Pflicht. ILO-Statistiken: 2,75 Mio. Tote/Jahr. Moralisch inakzeptabel.",
                        pl = "Obowiązek moralny. Statystyki MOP: 2,75 mln zgonów rocznie. Moralnie niedopuszczalne."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("2. Finansal Neden", "2. The Financial Reason", "2. Der finanzielle Grund", "2. Powód finansowy"),
                    content = LocalizedText(
                        tr = "Kazalar maliyetlidir. İkiye ayrılır:\n\n1. DOĞRUDAN MALİYETLER (Ölçülebilir): İlk yardım, hastalık ödemeleri, onarım masrafları, kayıp ürünler.\n\n2. DOLAYLI MALİYETLER (Gizli ve Büyük): Üretim gecikmeleri, sipariş kaybı, itibar kaybı (kamu imajı), personel moralinin bozulması, yasal soruşturma süresi.\n\nDolaylı maliyetleri ölçmek zordur ancak genellikle doğrudan maliyetlerden çok daha fazladır.",
                        en = "Accidents cost money.\n1. DIRECT Costs (Measurable): First aid, repairs, sick pay.\n2. INDIRECT Costs (Hidden): Delays, reputation damage, low morale, investigation time.\nIndirect costs are usually much higher.",
                        de = "Direkte Kosten (Erste Hilfe) vs. Indirekte Kosten (Rufschädigung, Verzögerungen).",
                        pl = "Koszty bezpośrednie (pierwsza pomoc) vs. Pośrednie (utrata reputacji, opóźnienia)."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("Buzdağı Teorisi", "The Iceberg Theory", "Die Eisberg-Theorie", "Teoria góry lodowej"),
                    content = LocalizedText(
                        tr = "Maliyetleri anlamak için en iyi benzetme 'Buzdağı'dır.\n\n- SİGORTALI (Görünen): Yangın, yaralanma tazminatı, tıbbi masraflar.\n- SİGORTASIZ (Suyun Altı): Üretim kaybı, yasal para cezaları (cezalar sigortalanamaz!), itibar kaybı, temizlik masrafları.\n\nHSE'ye göre sigortasız maliyetler, sigortalı olanlardan 8 ila 36 kat daha fazladır. Gizli maliyetler bir işletmeyi batırabilir.",
                        en = "Iceberg Theory:\n- INSURED (Tip): Injury compensation, medical costs.\n- UNINSURED (Underwater): Production loss, criminal fines (cannot be insured!), reputation damage.\nUninsured costs are 8-36 times greater.",
                        de = "Versicherte vs. Unversicherte Kosten (8-36 mal höher). Geldstrafen sind nicht versicherbar.",
                        pl = "Ubezpieczone vs. Nieubezpieczone koszty (8-36 razy wyższe). Grzywny nie są ubezpieczone."
                    ),
                    imageType = "warning"
                )
            )
        )
        neboshDao.insertTopic(Topic("1.1", "IG1", "Ahlak ve Para", "Morals and Money", "Moral und Geld", "Moralność i Pieniądze", Gson().toJson(lesson1_1)))
    }

    private suspend fun insertElement1Topic2() {
        val lesson1_2 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("1. Yasal Çerçeve ve ILO", "1. Legal Framework & ILO", "1. Rechtlicher Rahmen", "1. Ramy Prawne"),
                    content = LocalizedText(
                        tr = "İSG'yi yönetmenin yasal nedeni, yasalar çerçevesiyle ilgilidir. Küresel bir yasa yoktur ancak çoğu ülke BM'ye bağlı ILO (Uluslararası Çalışma Örgütü) standartlarını temel alır.\n\nİki temel standart vardır:\n1. C155 (Sözleşme): Bağlayıcıdır. Hedefleri belirler.\n2. R164 (Tavsiye): Detaylı rehberlik sağlar.\n\nTemel İlke: İşverenler, riskleri önlemek için 'Makul Ölçüde Uygulanabilir' (So Far As Is Reasonably Practicable) her şeyi yapmalıdır.",
                        en = "The legal reason relates to laws. Most countries follow ILO standards:\n1. C155 (Convention): Binding. Sets goals.\n2. R164 (Recommendation): Provides details.\n\nKey Principle: Employers must ensure safety 'So Far As Is Reasonably Practicable'.",
                        de = "Rechtlicher Rahmen basiert auf ILO-Standards C155 (Bindend) und R164. Grundsatz: 'Soweit vernünftigerweise durchführbar'.",
                        pl = "Ramy prawne oparte na standardach MOP C155 (Wiążąca) i R164. Zasada: 'O ile jest to racjonalnie wykonalne'."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("2. İşverenin Sorumlulukları", "2. Employer Responsibilities", "2. Arbeitgeberpflichten", "2. Obowiązki Pracodawcy"),
                    content = LocalizedText(
                        tr = "ILO C155 (Madde 16) ve R164'e göre işverenin mutlak görevleri şunlardır:\n\n- Güvenli işyeri, ekipman ve çalışma yöntemleri sağlamak.\n- Kimyasal, fiziksel ve biyolojik riskleri önlemek.\n- Ücretsiz Kişisel Koruyucu Donanım (KKD/PPE) ve uygun giysi sağlamak.\n- Yeterli bilgi, talimat, eğitim ve gözetim (supervision) sağlamak.\n- Aşırı fiziksel ve zihinsel yorgunluğu önlemek.\n- Acil durum prosedürleri (yangın, ilk yardım) oluşturmak.",
                        en = "Under C155 Art 16 & R164, employers MUST:\n- Provide safe place, equipment, and methods.\n- Control chemical/physical risks.\n- Provide PPE free of charge.\n- Provide information, instruction, training, and supervision.\n- Prevent fatigue.\n- Arrange emergency procedures.",
                        de = "Pflichten: Sicherer Arbeitsplatz, Ausrüstung, Chemikalienkontrolle, kostenlose PSA, Ausbildung, Überwachung.",
                        pl = "Obowiązki: Bezpieczne miejsce, sprzęt, kontrola chemikaliów, bezpłatne ŚOI, szkolenia, nadzór."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("3. İşçinin Görevleri ve Hakları", "3. Worker Duties & Rights", "3. Arbeitnehmerpflichten", "3. Obowiązki i Prawa Pracownika"),
                    content = LocalizedText(
                        tr = "İşçilerin de yasal görevleri vardır (C155 Madde 19):\n- Kendilerinin ve diğerlerinin güvenliğine makul özen göstermek.\n- Güvenlik talimatlarına uymak.\n- KKD'leri doğru kullanmak ve asla kurcalamamak.\n- Tehlikeleri derhal rapor etmek.\n\nHAKLARI:\n- Güvenlik hakkında tam bilgi ve eğitim alma hakkı.\n- Ciddi ve yakın bir tehlike durumunda işten kaçınma (işi durdurma) hakkı.",
                        en = "Worker Duties (C155 Art 19):\n- Take care of self and others.\n- Follow instructions.\n- Use PPE correctly (do not misuse).\n- Report hazards.\n\nRIGHTS:\n- Right to information/training.\n- Right to leave workplace in imminent danger.",
                        de = "Pflichten: Sorgfalt, Anweisungen befolgen, PSA nutzen. Rechte: Info, Arbeitsverweigerung bei Gefahr.",
                        pl = "Obowiązki: Dbałość, instrukcje, ŚOI. Prawa: Informacja, odmowa pracy w niebezpieczeństwie."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("4. Yasal Sonuçlar", "4. Legal Consequences", "4. Konsequenzen", "4. Konsekwencje Prawne"),
                    content = LocalizedText(
                        tr = "Yasalara uyulmaması iki tür sonuç doğurur:\n\n1. CEZA HUKUKU (Criminal Law): Devlet tarafından uygulanır. Amaç cezalandırmaktır. Sonuç: Para cezası veya hapis. SİGORTALANAMAZ.\n\n2. MEDENİ HUKUK (Civil Law): Bireyler tarafından açılır. Amaç zararı tazmin etmektir (Tazminat). İşçinin ihmali kanıtlaması gerekir. SİGORTALANABİLİR.\n\nAyrıca İcra Kurumları (Müfettişler), işyerini denetleyebilir, işi durdurma emri verebilir veya iyileştirme talep edebilir.",
                        en = "Non-compliance leads to:\n1. CRIMINAL Law (State): Punishment (Fine/Jail). UNINSURABLE.\n2. CIVIL Law (Individual): Compensation. INSURABLE.\n\nEnforcement agencies can inspect, stop work, or issue improvement notices.",
                        de = "Strafrecht (Geldstrafe/Gefängnis - nicht versicherbar) vs. Zivilrecht (Entschädigung - versicherbar).",
                        pl = "Prawo Karne (Grzywna/Więzienie - nieubezpieczone) vs. Cywilne (Odszkodowanie - ubezpieczone)."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("5. Diğer Standartlar (ISO)", "5. Other Standards (ISO)", "5. Andere Standards", "5. Inne Standardy"),
                    content = LocalizedText(
                        tr = "Yasal olmasa da, uluslararası standartlar iyi uygulamayı gösterir:\n\n- ISO 9001: Kalite Yönetimi.\n- ISO 14001: Çevre Yönetimi.\n- ISO 45001: İş Sağlığı ve Güvenliği Yönetim Sistemi (En önemlisi).\n\nISO 45001, diğerleriyle uyumludur ve PUKÖ (PDCA) döngüsünü temel alır. Bu sertifikaya sahip olmak müşterilere güven verir.",
                        en = "International standards demonstrate good practice:\n- ISO 9001: Quality.\n- ISO 14001: Environment.\n- ISO 45001: Health & Safety Management System.\n\nISO 45001 is based on PDCA cycle and is compatible with others.",
                        de = "ISO 45001 ist der Standard für Arbeitsschutzmanagementsysteme (basierend auf PDCA).",
                        pl = "ISO 45001 to standard SZBHP (oparty na PDCA)."
                    ),
                    imageType = "policy"
                )
            )
        )
        neboshDao.insertTopic(Topic("1.2", "IG1", "Hukuk ve Düzenleme", "Regulating H&S", "Rechtlicher Rahmen", "Regulacje Prawne", Gson().toJson(lesson1_2)))
    }

    private suspend fun insertElement1Topic3() {
        val lesson1_3 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("1. İşveren ve Yöneticiler", "1. Employer & Directors", "1. Arbeitgeber & Direktoren", "1. Pracodawca i Dyrektorzy"),
                    content = LocalizedText(
                        tr = "- İŞVEREN: Yasal sorumluluğun nihai sahibidir. Genellikle bir kişi değil, tüzel kişiliktir (Şirket).\n\n- YÖNETİCİLER (Directors): Organizasyona yön verir, politika belirler ve KAYNAK (para/insan) ayırır. Liderlikleri çok önemlidir.\n\n- ORTA KADEME YÖNETİCİLER: Stratejiyi günlük operasyonlara döker. Kendi alanlarındaki İSG standartlarından sorumludurlar.",
                        en = "- EMPLOYER: Ultimate responsibility. Usually a legal entity (Company).\n- DIRECTORS: Set direction/policy and allocate RESOURCES. Leadership is key.\n- MANAGERS: Operationalize strategy. Responsible for standards in their areas.",
                        de = "Arbeitgeber (Verantwortung), Direktoren (Strategie & Ressourcen), Manager (Operativ).",
                        pl = "Pracodawca (Odpowiedzialność), Dyrektorzy (Strategia/Zasoby), Menedżerowie (Operacyjnie)."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("2. Paylaşılan İşyerleri", "2. Shared Workplaces", "2. Geteilte Arbeitsplätze", "2. Wspólne Miejsca Pracy"),
                    content = LocalizedText(
                        tr = "İki veya daha fazla işveren bir işyerini paylaştığında (örn: Ofis binası veya Şantiye), ILO C155 (Madde 17) gereği İŞBİRLİĞİ yapmalıdırlar.\n\n- Riskler hakkında bilgi alışverişi yapılmalıdır.\n- Faaliyetler koordine edilmelidir (örn: Yangın tatbikatları, Trafik yönetimi).\n- Ortak bir 'Bina Yönetim Komitesi' kurulabilir.",
                        en = "When employers share a workplace (ILO C155 Art 17), they MUST cooperate.\n- Exchange info on risks.\n- Coordinate activities (Fire drills, Traffic).\n- Establish a management committee.",
                        de = "Geteilte Arbeitsplätze erfordern Zusammenarbeit und Koordination (z.B. Feueralarm).",
                        pl = "Wspólne miejsca pracy wymagają współpracy i koordynacji (np. Alarmy pożarowe)."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("3. Yüklenici Yönetimi: Sorumluluk", "3. Contractor Management", "3. Auftragnehmermanagement", "3. Zarządzanie Wykonawcami"),
                    content = LocalizedText(
                        tr = "Yüklenici (Contractor), müşteri adına iş yapan kişidir. Sorumluluk PAYLAŞILIR.\n\n- Müşteri, kendi işyerindeki tehlikelerden yükleniciyi haberdar etmelidir.\n- Yüklenici, kendi işinin yaratacağı risklerden müşteriyi haberdar etmelidir.\n\nSüreç 3 aşamalıdır: 1. Seçim, 2. Planlama, 3. İzleme.",
                        en = "Responsibility is SHARED between Client and Contractor.\n- Client must inform Contractor of site hazards.\n- Contractor must inform Client of work risks.\n\n3 Stages: 1. Selection, 2. Planning, 3. Monitoring.",
                        de = "Geteilte Verantwortung. 3 Phasen: Auswahl, Planung, Überwachung.",
                        pl = "Wspólna odpowiedzialność. 3 etapy: Wybór, Planowanie, Monitorowanie."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("4. Seçim, Planlama, İzleme", "4. Select, Plan, Monitor", "4. Auswahl, Planung, Überwachung", "4. Wybór, Planowanie, Monitorowanie"),
                    content = LocalizedText(
                        tr = "1. SEÇİM (Selection): Sadece fiyata değil, GÜVENLİĞE bak. (İstenenler: İSG Politikası, Risk Değerlendirmeleri, Kaza Kayıtları, Referanslar).\n\n2. PLANLAMA (Planning): İş başlamadan önce tehlikeleri konuş. 'Yöntem Beyanı' (Method Statement) üzerinde anlaş.\n\n3. İZLEME (Monitoring): Kurallara uyuyorlar mı? (Giriş-çıkış kontrolü, İşe başlangıç eğitimi, Çalışma İzni sistemi).",
                        en = "1. SELECTION: Check Competence (Policy, Risk Assessments, Accident History).\n2. PLANNING: Agree on Method Statement/Risk Assessment.\n3. MONITORING: Check compliance (Induction, Permits, Supervision).",
                        de = "1. Auswahl (Kompetenz), 2. Planung (Methoden), 3. Überwachung (Einhaltung).",
                        pl = "1. Wybór (Kompetencje), 2. Planowanie (Metody), 3. Monitorowanie (Zgodność)."
                    ),
                    imageType = "warning"
                )
            )
        )
        neboshDao.insertTopic(Topic("1.3", "IG1", "Roller ve Yükleniciler", "Roles & Contractors", "Rollen & Auftragnehmer", "Role i Wykonawcy", Gson().toJson(lesson1_3)))
    }

    // --- ELEMENT 2 ---
    private suspend fun insertElement2Topics() {
        val lesson2_1 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("İSG Yönetim Sistemlerine Giriş", "Introduction to OHSMS", "Einführung in AMS", "Wprowadzenie do SZBHP"),
                    content = LocalizedText(
                        tr = "Büyük ve karmaşık kuruluşlarda iş sağlığı ve güvenliği 'şansa' bırakılamaz. Tıpkı finans veya kalite gibi sistematik bir şekilde yönetilmelidir. Buna İş Sağlığı ve Güvenliği Yönetim Sistemi (OHSMS) denir.\n\nDünya çapında kabul görmüş iki ana standart vardır:\n1. ILO-OSH 2001 (Uluslararası Çalışma Örgütü)\n2. ISO 45001 (Uluslararası Standartlar Örgütü)\n\nHer iki sistem de aynı temel üzerine kuruludur: PUKÖ (PDCA) Döngüsü.",
                        en = "Health and safety cannot be left to chance in large organizations. It must be managed systematically, just like finance or quality. This is called an Occupational Health and Safety Management System (OHSMS).\n\nThere are two globally recognized standards:\n1. ILO-OSH 2001\n2. ISO 45001\n\nBoth are built on the same foundation: The PDCA Cycle.",
                        de = "Einführung in Arbeitsschutzmanagementsysteme (AMS). ILO-OSH 2001 und ISO 45001 basieren auf dem PDCA-Zyklus.",
                        pl = "Wprowadzenie do systemów zarządzania BHP. ILO-OSH 2001 i ISO 45001 opierają się na cyklu PDCA."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("PUKÖ Döngüsü Nedir?", "What is the PDCA Cycle?", "Was ist der PDCA-Zyklus?", "Co to jest cykl PDCA?"),
                    content = LocalizedText(
                        tr = "PUKÖ (Planla - Uygula - Kontrol Et - Önlem Al) döngüsü, sürekli iyileştirmenin anahtarıdır.\n\n1. PLANLA (Plan): Neyi başarmak istiyoruz? Amaçlarımızı ve hedeflerimizi belirleriz. Riskleri nasıl yöneteceğimizi planlarız.\n\n2. UYGULA (Do): Planı hayata geçiririz. Risk değerlendirmeleri yapar, eğitimler verir ve prosedürleri uygularız.\n\n3. KONTROL ET (Check): İşler yolunda gidiyor mu? Performansımızı izleriz (Kaza istatistikleri, denetimler).\n\n4. ÖNLEM AL (Act): Hatalardan ders çıkarırız. Eğer hedeflere ulaşamadıysak sistemi gözden geçirir ve düzeltiriz.",
                        en = "The PDCA (Plan - Do - Check - Act) cycle is the key to continuous improvement.\n\n1. PLAN: Set objectives and targets. How will we manage risks?\n2. DO: Implement the plan. Conduct risk assessments, training, procedures.\n3. CHECK: Monitor performance. Are we meeting targets? (Audits, stats).\n4. ACT: Review and improve. Learn from mistakes and adjust the system.",
                        de = "Planen, Tun, Überprüfen, Handeln.",
                        pl = "Planuj, Wykonaj, Sprawdź, Działaj."
                    ),
                    imageType = "pdca"
                ),
                LessonSection(
                    title = LocalizedText("ILO-OSH 2001 Modeli", "The ILO-OSH 2001 Model", "Das ILO-OSH 2001 Modell", "Model MOP-BHP 2001"),
                    content = LocalizedText(
                        tr = "ILO modeli şu adımlardan oluşur:\n\n- Politika (Plan): Açık bir yönetim taahhüdü.\n- Organizasyon (Plan): Roller ve sorumlulukların dağıtılması.\n- Planlama ve Uygulama (Uygula): Risklerin belirlenmesi ve önlemlerin alınması.\n- Değerlendirme (Kontrol Et): İzleme, ölçme ve denetim.\n- İyileştirme Eylemi (Önlem Al): Denetim sonuçlarına göre düzeltici faaliyetler.\n- Denetim (Audit): Sistemin bağımsız bir gözle incelenmesi.",
                        en = "The ILO model steps:\n- Policy (Plan): Commitment.\n- Organizing (Plan): Roles.\n- Planning & Implementation (Do): Risk controls.\n- Evaluation (Check): Monitoring.\n- Action for Improvement (Act): Corrections.\n- Audit: Independent review.",
                        de = "Politik, Organisation, Planung, Bewertung, Verbesserung.",
                        pl = "Polityka, Organizacja, Planowanie, Ocena, Działania naprawcze."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("ISO 45001 Standardı", "ISO 45001 Standard", "ISO 45001 Norm", "Standard ISO 45001"),
                    content = LocalizedText(
                        tr = "ISO 45001, işletmelerin sertifika alabileceği (akredite edilebileceği) bir standarttır. Bu sertifikaya sahip olmak, müşterilere güven verir ve rekabet avantajı sağlar.\n\nYapısı diğer ISO standartlarıyla (9001 Kalite, 14001 Çevre) uyumludur. En önemli farkı, 'Liderlik ve Çalışan Katılımı'nı merkeze koymasıdır. Üst yönetim sadece imza atmakla kalmamalı, sürece aktif olarak liderlik etmelidir.",
                        en = "ISO 45001 is a certifiable standard. It provides competitive advantage.\n\nIt is compatible with ISO 9001 and 14001. Its key feature is placing 'Leadership and Worker Participation' at the center. Top management must actively lead, not just sign documents.",
                        de = "Zertifizierbarer Standard. Fokus auf Führung und Mitarbeiterbeteiligung.",
                        pl = "Certyfikowany standard. Skupia się na przywództwie i udziale pracowników."
                    ),
                    imageType = "policy"
                )
            )
        )
        neboshDao.insertTopic(Topic("2.1", "IG1", "Yönetim Sistemleri (ILO & ISO)", "Management Systems (ILO & ISO)", "Managementsysteme", "Systemy Zarządzania", Gson().toJson(lesson2_1)))

        val lesson2_2 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("İSG Politikası Nedir?", "What is H&S Policy?", "Was ist Sicherheitspolitik?", "Co to jest Polityka BHP?"),
                    content = LocalizedText(
                        tr = "Politika, bir kurumun İSG yönetim sisteminin temel taşıdır. Kurumun hedeflerini ve bu hedeflere kimin, nasıl ulaşacağını belirleyen çok önemli bir belgedir.\n\nILO R164 (Madde 14) gereği, politika YAZILI olmalı ve tüm çalışanların anlayacağı dilde kendilerine duyurulmalıdır. Genellikle 3 ana bölümden oluşur.",
                        en = "The Policy is the cornerstone of the OHSMS. It sets objectives and responsibilities.\n\nUnder ILO R164 (Art 14), it must be WRITTEN and communicated to all workers in a language they understand. It consists of 3 main parts.",
                        de = "Die Politik ist der Eckpfeiler. Muss schriftlich sein (ILO R164).",
                        pl = "Polityka jest kamieniem węgielnym. Musi być pisemna (MOP R164)."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("Bölüm 1: Genel Niyet Beyanı", "Part 1: Statement of Intent", "Teil 1: Absichtserklärung", "Część 1: Oświadczenie o intencjach"),
                    content = LocalizedText(
                        tr = "Bu bölüm kurumun VİZYONUDUR ('Ne yapacağız?').\n\n- Kurumun İSG'ye verdiği önemi gösterir.\n- Yasalara uyum taahhüdü içerir.\n- Hedefleri belirler (Örn: 'Sıfır Kaza' veya 'Kaza oranını %10 düşürmek').\n\nEN ÖNEMLİSİ: Bu bölüm, kurumun en tepesindeki kişi (CEO veya Genel Müdür) tarafından imzalanmalı ve tarih atılmalıdır. Bu, en üst düzeyin taahhüdünü gösterir.",
                        en = "This is the VISION ('What are we going to do?').\n\n- Shows commitment.\n- Commits to legal compliance.\n- Sets targets (e.g., 'Zero Harm').\n\nMOST IMPORTANT: Must be signed and dated by the top person (CEO/MD).",
                        de = "Vision. Unterschrieben vom CEO.",
                        pl = "Wizja. Podpisane przez CEO."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("SMART Hedefler", "SMART Targets", "SMART-Ziele", "Cele SMART"),
                    content = LocalizedText(
                        tr = "Politikada belirlenen hedefler SMART kuralına uymalıdır:\n\n- Specific (Özel): Net olmalı.\n- Measurable (Ölçülebilir): Sayılarla takip edilebilmeli.\n- Achievable (Ulaşılabilir): İmkansız olmamalı.\n- Reasonable (Makul): Kaynaklar yeterli olmalı.\n- Time-bound (Zaman Sınırlı): Bir son tarihi olmalı (Örn: 'Yıl sonuna kadar').\n\nÖrnek: 'Güvenlik kültürünü iyileştirmek' SMART değildir. Ancak 'Tüm risk değerlendirmelerini 12 ay içinde yenilemek' SMART bir hedeftir.",
                        en = "Targets must be SMART:\n\n- Specific\n- Measurable\n- Achievable\n- Reasonable\n- Time-bound\n\nExample: 'Improve safety' is NOT SMART. 'Review all risk assessments within 12 months' IS SMART.",
                        de = "Ziele müssen SMART sein (Spezifisch, Messbar, Erreichbar, Vernünftig, Zeitgebunden).",
                        pl = "Cele muszą być SMART (Konkretne, Mierzalne, Osiągalne, Rozsądne, Terminowe)."
                    ),
                    imageType = "pdca"
                ),
                LessonSection(
                    title = LocalizedText("Bölüm 2: Organizasyon (Roller)", "Part 2: Organization (Roles)", "Teil 2: Organisation", "Część 2: Organizacja"),
                    content = LocalizedText(
                        tr = "Bu bölüm 'Kim yapacak?' sorusunu yanıtlar. Sorumluluk zincirini (hiyerarşiyi) gösterir.\n\n- CEO: Nihai yasal sorumluluk.\n- Yöneticiler: Kaynak ayırma ve strateji.\n- Orta Kademe: Operasyonel uygulama.\n- Tüm Çalışanlar: Kendine ve başkalarına dikkat etme, kurallara uyma.\n- İSG Uzmanı: Yetkili tavsiye ve rehberlik verme.",
                        en = "Answers 'Who?'. Shows the chain of command.\n- CEO: Ultimate responsibility.\n- Managers: Resources.\n- Workers: Safe acts.\n- H&S Advisor: Guidance.",
                        de = "Wer macht was? Rollen und Verantwortlichkeiten.",
                        pl = "Kto co robi? Role i odpowiedzialność."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("Bölüm 3: Düzenlemeler", "Part 3: Arrangements", "Teil 3: Regelungen", "Część 3: Ustalenia"),
                    content = LocalizedText(
                        tr = "Bu bölüm 'Nasıl yapacağız?' sorusunu yanıtlar. Politikanın en uzun ve detaylı kısmıdır. Sistem ve prosedürleri içerir.\n\nGenel Düzenlemeler:\n- Risk değerlendirmesi nasıl yapılır?\n- Eğitimler nasıl verilir?\n- Kazalar nasıl raporlanır?\n- İlk yardım ve acil durum planları.\n\nÖzel Düzenlemeler (Riskli İşler):\n- Yalnız çalışma, Gürültü, Kimyasallar, Yüksekte çalışma prosedürleri.",
                        en = "Answers 'How?'. Contains detailed procedures.\nGeneral: Risk assessment, Training, Accident reporting.\nSpecific: Lone working, Noise, Chemicals.",
                        de = "Wie? Detaillierte Verfahren (Risikobewertung, Training).",
                        pl = "Jak? Szczegółowe procedury (Ocena ryzyka, Szkolenia)."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("Politikayı Gözden Geçirme", "Reviewing the Policy", "Überprüfung der Politik", "Przegląd Polityki"),
                    content = LocalizedText(
                        tr = "Politika 'canlı' bir belgedir, asla rafa kaldırılıp unutulmamalıdır. Şu durumlarda GÖZDEN GEÇİRİLMELİDİR:\n\n- Teknolojik değişiklikler (yeni makine/süreç).\n- Organizasyonel değişiklikler (yeni CEO, yeniden yapılanma).\n- Yasal değişiklikler (yeni kanunlar).\n- Kazalar veya denetimler sonrası (sistem hatası varsa).\n- Belirli bir süre geçtiğinde (örn. Yıllık periyodik inceleme).",
                        en = "Policy is a 'live' document. REVIEW when:\n- Tech changes.\n- Organizational changes.\n- Legal changes.\n- After accidents.\n- Periodically (Annually).",
                        de = "Politik muss überprüft werden bei Änderungen oder Unfällen.",
                        pl = "Polityka musi być przeglądana przy zmianach lub wypadkach."
                    ),
                    imageType = "policy"
                )
            )
        )
        neboshDao.insertTopic(Topic("2.2", "IG1", "İSG Politikası", "Health & Safety Policy", "Sicherheitspolitik", "Polityka BHP", Gson().toJson(lesson2_2)))
    }

    // --- ELEMENT 3 ---
    private suspend fun insertElement3Topics() {
        val lesson3_1 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Sağlık ve Güvenlik Kültürü Nedir?", "What is H&S Culture?", "Was ist Sicherheitskultur?", "Co to jest Kultura BHP?"),
                    content = LocalizedText(
                        tr = "Kültür yazılı değildir, bir hissetme biçimidir. Genellikle 'Burada işlerin yapılma şekli' olarak tanımlanır.\n\nResmi Tanım: 'Sağlık ve güvenlikle ilgili paylaşılan tutumlar, değerler, inançlar ve davranışlardır.'\n\nKültür, organizasyon içindeki herkesin (üst yönetimden işçiye kadar) sağlık ve güvenlik hakkında ne düşündüğü ve ne hissettiğidir. Bu düşünceler doğrudan davranışları etkiler.",
                        en = "Culture is not written; it's a feeling. Often defined as 'The way we do things around here'.\n\nFormal Definition: 'Shared attitudes, values, beliefs and behaviours relating to health and safety.'\n\nIt is how everyone thinks and feels about safety, which directly drives behaviour.",
                        de = "Kultur ist 'die Art, wie wir Dinge hier tun'. Geteilte Einstellungen, Werte und Überzeugungen.",
                        pl = "Kultura to 'sposób, w jaki tu postępujemy'. Wspólne postawy, wartości i przekonania."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("Pozitif ve Negatif Kültür", "Positive vs Negative Culture", "Positive vs. Negative Kultur", "Kultura Pozytywna vs Negatywna"),
                    content = LocalizedText(
                        tr = "POZİTİF KÜLTÜR:\n- İnsanlar sağlık ve güvenliğin önemli olduğuna inanır.\n- Yönetim liderlik eder ve örnek olur.\n- İnsanlar 'zorunda oldukları için' değil, 'istedikleri için' güvenli çalışırlar (gözetim olmasa bile).\n\nNEGATİF KÜLTÜR:\n- İnsanlar güvenliği gereksiz bir engel olarak görür.\n- Liderlik zayıftır, yönetim sadece kâra odaklanır.\n- Bir 'suçlama kültürü' vardır.\n- İşçiler ne yapacaklarını bilmedikleri veya umursamadıkları için güvensiz çalışırlar.",
                        en = "POSITIVE: People believe safety is important. Management leads. People work safely because they WANT to.\n\nNEGATIVE: Safety is seen as an obstacle. Poor leadership. Blame culture exists. People work unsafely because they don't know or don't care.",
                        de = "Positiv: Sicherheit ist wichtig. Negativ: Sicherheit ist ein Hindernis, schlechte Führung.",
                        pl = "Pozytywna: Bezpieczeństwo jest ważne. Negatywna: Bezpieczeństwo to przeszkoda, słabe przywództwo."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("Kültür Göstergeleri (1)", "Indicators of Culture (1)", "Indikatoren der Kultur (1)", "Wskaźniki Kultury (1)"),
                    content = LocalizedText(
                        tr = "Kültür soyut olduğu için (düşünceler ve hisler), onu ölçmek zordur. Bu yüzden somut 'Göstergelere' bakarız:\n\n1. KAZALAR: Kaza oranları artıyor mu azalıyor mu? Ulusal ortalamanın altında mı?\n2. HASTALIK ORANLARI: İş kaynaklı hastalıklar (örn. sırt ağrısı, stres) yüksek mi?\n3. DEVAMSIZLIK: Yüksek devamsızlık, düşük moralin ve kötü bir kültürün işareti olabilir (insanlar işe gelmek istemiyor).",
                        en = "Culture is hard to measure directly. We look at indicators:\n1. ACCIDENTS: Are rates going up or down?\n2. SICKNESS RATES: Is work-related ill-health high?\n3. ABSENTEEISM: High absenteeism indicates low morale and poor culture.",
                        de = "Indikatoren: Unfälle, Krankheitsraten, Fehlzeiten (Moral).",
                        pl = "Wskaźniki: Wypadki, Wskaźniki chorobowe, Absencja (Morale)."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("Kültür Göstergeleri (2)", "Indicators of Culture (2)", "Indikatoren der Kultur (2)", "Wskaźniki Kultury (2)"),
                    content = LocalizedText(
                        tr = "4. PERSONEL DEVİR HIZI (Turnover): İşçiler mutsuzsa ve güvende hissetmiyorsa işten ayrılırlar. Yüksek devir hızı kötü kültürün işaretidir.\n5. KURALLARA UYUM: Güvenlik denetimlerinde kurallara uyulduğu görülüyor mu? Yoksa kurallar çiğneniyor mu?\n6. ŞİKAYETLER: Çalışma koşulları hakkında çok fazla şikayet var mı?",
                        en = "4. STAFF TURNOVER: If workers feel unsafe, they leave. High turnover = bad culture.\n5. COMPLIANCE: Are safety rules followed during audits?\n6. COMPLAINTS: Are there many complaints about working conditions?",
                        de = "Personalfluktuation, Einhaltung von Regeln, Beschwerden.",
                        pl = "Rotacja pracowników, Przestrzeganie zasad, Skargi."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("Akran Grubu Baskısı", "Peer Group Pressure", "Gruppenzwang", "Presja Rówieśnicza"),
                    content = LocalizedText(
                        tr = "İnsanlar sosyal varlıklardır ve grup içinde etkileşime girerler. Bir grubun 'Normları' (davranış biçimleri) vardır. Yeni bir kişi gruba girdiğinde, kabul görmek için bu normlara uymaya zorlanır. Buna 'Akran Baskısı' denir.\n\n- Eğer grup GÜVENLİ çalışıyorsa, yeni kişiyi de güvenli çalışmaya zorlar (Pozitif Etki).\n- Eğer grup GÜVENSİZ çalışıyorsa, yeni kişi doğruyu bilse bile gruba uymak için güvensiz çalışacaktır (Negatif Etki).",
                        en = "People conform to group norms to fit in. This is 'Peer Pressure'.\n- If the group works SAFELY, they influence new workers positively.\n- If the group works UNSAFELY, they force new workers to be unsafe to fit in.",
                        de = "Gruppenzwang: Menschen passen sich der Gruppe an (positiv oder negativ).",
                        pl = "Presja rówieśnicza: Ludzie dostosowują się do grupy (pozytywnie lub negatywnie)."
                    ),
                    imageType = "pdca"
                )
            )
        )
        neboshDao.insertTopic(Topic("3.1", "IG1", "Sağlık ve Güvenlik Kültürü", "Health & Safety Culture", "Sicherheitskultur", "Kultura BHP", Gson().toJson(lesson3_1)))
    }

    private suspend fun insertElement3Topic2() {
        val lesson3_2 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("1. Yönetim Liderliği", "1. Management Leadership", "1. Führung", "1. Przywództwo"),
                    content = LocalizedText(
                        tr = "Kültürü geliştirmenin en önemli yolu 'Görünür Liderlik'tir. Yöneticiler sadece kuralları koymakla kalmamalı, onlara uymalıdır (Örnek olma).\n\nLiderlik şunları içerir:\n- Güvenlik turlarına katılmak.\n- Güvenlik toplantılarına başkanlık etmek.\n- Güvenli davranışı ödüllendirmek, güvensiz davranışı düzeltmek.\n\nBir 'Suçlama Kültürü'nden kaçınılmalıdır. Ancak sürekli kural ihlali yapanlara adil bir disiplin süreci uygulanmalıdır.",
                        en = "The key to improving culture is 'Visible Leadership'. Managers must lead by example.\nIncludes:\n- Safety tours.\n- Chairing meetings.\n- Rewarding safe behaviour.\n\nAvoid a 'Blame Culture', but apply fair discipline for repeated violations.",
                        de = "Sichtbare Führung ist der Schlüssel. Vorbild sein, Sicherheitstouren, faire Disziplin.",
                        pl = "Widoczne przywództwo to klucz. Dawanie przykładu, obchody, sprawiedliwa dyscyplina."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("2. Yetkin Çalışanlar", "2. Competent Employees", "2. Kompetente Mitarbeiter", "2. Kompetentni Pracownicy"),
                    content = LocalizedText(
                        tr = "Yetkinlik, bir işi güvenli yapmak için gereken 4 unsurun birleşimidir (KATE):\n\nK - Knowledge (Bilgi)\nA - Ability (Yetenek/Beceri)\nT - Training (Eğitim)\nE - Experience (Deneyim)\n\nSadece eğitimli olmak yetmez, deneyim de şarttır. Yöneticiler de yetkin olmalıdır; örneğin bir depo müdürü forklift kullanmayı bilmese bile, güvenli ve güvensiz kullanım arasındaki farkı anlayacak bilgiye sahip olmalıdır.",
                        en = "Competence = K.A.T.E (Knowledge, Ability, Training, Experience).\nTraining alone is not enough. Managers must also be competent to spot unsafe practices.",
                        de = "Kompetenz = Wissen, Fähigkeit, Ausbildung, Erfahrung (KATE).",
                        pl = "Kompetencje = Wiedza, Umiejętności, Szkolenie, Doświadczenie."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("3. Etkili İletişim", "3. Effective Communication", "3. Effektive Kommunikation", "3. Skuteczna Komunikacja"),
                    content = LocalizedText(
                        tr = "Bilgiyi iletmenin 3 yolu vardır:\n\n1. SÖZLÜ (Verbal): Hızlıdır, geri bildirim alınır (soru-cevap). Ancak kaydı yoktur ve yanlış anlaşılabilir (gürültü, dil engeli).\n2. YAZILI (Written): Kalıcı kayıttır, detaylıdır. Ancak okunmayabilir, hazırlaması zaman alır ve jargon içerebilir.\n3. GÖRSEL (Graphic): Posterler, işaretler. Dil engelini aşar, dikkat çeker. Ancak sadece basit mesajlar verebilir.",
                        en = "1. VERBAL: Fast, feedback possible. No record, noise barriers.\n2. WRITTEN: Permanent record. May not be read, time-consuming.\n3. GRAPHIC: Overcomes language barriers. Only simple messages.",
                        de = "Mündlich (schnell), Schriftlich (Rekord), Grafisch (keine Sprachbarriere).",
                        pl = "Ustna (szybka), Pisemna (dokumentacja), Graficzna (brak barier językowych)."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("4. İşbirliği ve Danışma", "4. Cooperation & Consultation", "4. Zusammenarbeit", "4. Współpraca"),
                    content = LocalizedText(
                        tr = "Danışma, işveren ve işçi arasında İKİ YÖNLÜ bir süreçtir (Sadece bilgi vermek değil, dinlemektir).\n\nYöntemler:\n- Doğrudan Danışma: Birebir konuşma (Küçük işyerleri için).\n- İşçi Temsilcileri aracılığıyla: Güvenlik Komitesi kurulur (Büyük işyerleri için).\n\nGüvenlik Komitesi; kazaları, denetim raporlarını ve yeni ekipmanları tartışmak için düzenli toplanmalıdır.",
                        en = "Consultation is a TWO-WAY process (Listening, not just telling).\nMethods:\n- Direct (Small firms).\n- Via Representatives (Safety Committee).\nCommittees should meet regularly to discuss accidents and reports.",
                        de = "Konsultation ist ein ZWEI-WEGE-Prozess. Sicherheitsausschuss.",
                        pl = "Konsultacje to proces DWUKIERUNKOWY. Komitet Bezpieczeństwa."
                    ),
                    imageType = "pdca"
                ),
                LessonSection(
                    title = LocalizedText("5. Eğitim Ne Zaman Verilmeli?", "5. When to Train?", "5. Wann trainieren?", "5. Kiedy szkolić?"),
                    content = LocalizedText(
                        tr = "Eğitim şu durumlarda ZORUNLUDUR:\n\n1. İŞE GİRİŞ (Induction): Yeni başlayanlar için. Öncelik: Acil durumlar (yangın), ilk yardım, güvenli yollar.\n2. İŞ DEĞİŞİKLİĞİ: Yeni görevler ve riskler (örn. terfi).\n3. SÜREÇ DEĞİŞİKLİĞİ: Yeni yöntemler.\n4. YENİ TEKNOLOJİ: Yeni makineler.\n5. YENİ MEVZUAT: Yasalar değiştiğinde.\n\nEğitim kayıtları mutlaka saklanmalıdır.",
                        en = "Training is MANDATORY when:\n1. INDUCTION (New starters - Emergency first!).\n2. JOB CHANGE.\n3. PROCESS CHANGE.\n4. NEW TECHNOLOGY.\n5. LEGISLATION CHANGE.\nKeep records.",
                        de = "Induktion, Jobwechsel, Prozessänderung, Neue Technologie, Neue Gesetze.",
                        pl = "Wdrożenie, Zmiana pracy, Zmiana procesu, Nowa technologia, Nowe przepisy."
                    ),
                    imageType = "hazard"
                )
            )
        )
        neboshDao.insertTopic(Topic("3.2", "IG1", "Kültürün Geliştirilmesi", "Improving Culture", "Kultur verbessern", "Poprawa Kultury", Gson().toJson(lesson3_2)))
    }

    private suspend fun insertElement3Topic3() {
        val lesson3_3 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("İnsan Faktörleri Nedir?", "What are Human Factors?", "Was sind menschliche Faktoren?", "Czym są czynniki ludzkie?"),
                    content = LocalizedText(
                        tr = "İnsan faktörleri, işin, organizasyonun ve bireyin özelliklerinin güvenli davranışı nasıl etkilediğidir. Kazalar genellikle 'İnsan Hatası' olarak geçiştirilir ama arkasında bu faktörler yatar.\n\n3 Ana Faktör vardır:\n1. Örgütsel Faktörler (Organizational)\n2. İş Faktörleri (Job)\n3. Bireysel Faktörler (Individual)\n\nFormül: Örgüt + İş + Birey = Güvenli/Güvensiz Davranış.",
                        en = "Human factors refer to how the job, the organization, and the individual influence behavior. Accidents are rarely just 'human error'.\n\n3 Key Factors:\n1. Organizational\n2. Job\n3. Individual\n\nFormula: Org + Job + Individual = Behaviour.",
                        de = "3 Faktoren: Organisation, Arbeit, Individuum. Diese bestimmen das Verhalten.",
                        pl = "3 Czynniki: Organizacja, Praca, Jednostka. One określają zachowanie."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("1. Örgütsel Faktörler", "1. Organizational Factors", "1. Organisatorische Faktoren", "1. Czynniki Organizacyjne"),
                    content = LocalizedText(
                        tr = "Bunlar yönetimin kontrolündeki faktörlerdir ve en büyük etkiye sahiptir:\n\n- Güvenlik Kültürü: Pozitif mi negatif mi?\n- Liderlik: Yöneticiler örnek oluyor mu?\n- Kaynaklar: Yeterli zaman, para ve ekipman veriliyor mu?\n- İletişim: Bilgi açıkça paylaşılıyor mu?\n- Denetim: Kurallar uygulanıyor mu?\n\nEğer yönetim güvenliği umursamıyorsa, işçi de umursamaz.",
                        en = "Factors controlled by management (Biggest influence):\n- Culture\n- Leadership (Example set?)\n- Resources (Time/Money)\n- Communication\n- Supervision\nIf management doesn't care, workers won't care.",
                        de = "Management-Faktoren: Kultur, Führung, Ressourcen, Kommunikation.",
                        pl = "Czynniki zarządzania: Kultura, Przywództwo, Zasoby, Komunikacja."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("2. İş (Görev) Faktörleri", "2. Job Factors", "2. Arbeitsfaktoren", "2. Czynniki Pracy"),
                    content = LocalizedText(
                        tr = "İşin doğası ve çevresel koşullarla ilgilidir:\n\n- Ergonomi: Ekipman insana uygun mu? (Örn: Düğmeler erişilebilir mi?)\n- Çevre: Gürültü, sıcaklık, aydınlatma. (Gürültülü ortamda uyarıları duyamazsınız).\n- Görev: İş çok mu sıkıcı (dikkat dağılır) veya çok mu karmaşık (hata artar)?\n- Prosedürler: Talimatlar net mi yoksa kafa karıştırıcı mı?",
                        en = "Nature of the task and environment:\n- Ergonomics (Design matches human?)\n- Environment (Noise, heat, light)\n- Task (Too boring -> distraction; Too complex -> mistakes)\n- Procedures (Clear?)",
                        de = "Ergonomie, Umgebung (Lärm/Licht), Aufgabe (Langeweile/Stress).",
                        pl = "Ergonomia, Środowisko (hałas/światło), Zadanie (nuda/stres)."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("3. Bireysel Faktörler", "3. Individual Factors", "3. Individuelle Faktoren", "3. Czynniki Indywidualne"),
                    content = LocalizedText(
                        tr = "Kişinin işe getirdiği özelliklerdir. Değiştirilmesi en zor olandır:\n\n- Yetkinlik (Competence): Bilgi ve deneyim eksikliği.\n- Tutum (Attitude): 'Bana bir şey olmaz' düşüncesi.\n- Motivasyon: Güvenli çalışmak istiyor mu?\n- Fiziksel Yetenek: Boy, güç, görme/duyma yetisi.\n- Risk Algısı: Tehlikeyi görebiliyor mu? (Yorgunluk, stres ve uyuşturucu algıyı bozar).",
                        en = "Characteristics the person brings:\n- Competence (Knowledge/Exp)\n- Attitude ('It won't happen to me')\n- Motivation\n- Physical Capability\n- Risk Perception (Affected by fatigue, stress, drugs).",
                        de = "Kompetenz, Einstellung, Motivation, Physische Fähigkeiten.",
                        pl = "Kompetencje, Postawa, Motywacja, Zdolności fizyczne."
                    ),
                    imageType = "pdca"
                ),
                LessonSection(
                    title = LocalizedText("İnsan Hatası Türleri", "Types of Human Error", "Arten menschlicher Fehler", "Rodzaje Błędów Ludzkich"),
                    content = LocalizedText(
                        tr = "İnsanlar neden hata yapar?\n\n1. İSTEM DIŞI (Unintentional):\n   - Sürçme (Slip): Eylem hatası (Düğmeye basarken elin kayması).\n   - Dalgınlık (Lapse): Hafıza hatası (Unutmak).\n\n2. İSTEMLİ (Intentional):\n   - Hata (Mistake): Niyet doğru ama yöntem yanlış (Bilgi eksikliği).\n   - İhlal (Violation): Bilerek kural çiğnemek (Kestirme yol kullanmak, KKD takmamak).",
                        en = "Why do we fail?\n1. UNINTENTIONAL:\n   - Slip (Action failure)\n   - Lapse (Memory failure)\n2. INTENTIONAL:\n   - Mistake (Right intent, wrong plan)\n   - Violation (Deliberate rule breaking).",
                        de = "Unbeabsichtigt (Ausrutscher/Vergesslichkeit) vs. Beabsichtigt (Fehler/Verstoß).",
                        pl = "Niezamierzone (Poślizgnięcie/Zapomnienie) vs. Zamierzone (Błąd/Naruszenie)."
                    ),
                    imageType = "warning"
                )
            )
        )
        neboshDao.insertTopic(Topic("3.3", "IG1", "İnsan Faktörleri", "Human Factors", "Menschliche Faktoren", "Czynniki Ludzkie", Gson().toJson(lesson3_3)))
    }

    private suspend fun insertElement3Topic4() {
        val lesson3_4 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Risk Değerlendirmesi Nedir?", "What is Risk Assessment?", "Was ist Risikobeurteilung?", "Co to jest Ocena Ryzyka?"),
                    content = LocalizedText(
                        tr = "Risk değerlendirmesi, işyerindeki nelerin insanlara zarar verebileceğini incelediğimiz ve yeterli önlem alıp almadığımıza karar verdiğimiz süreçtir. Amaç, kazaları ve hastalıkları önlemektir.\n\nTemel Tanımlar:\n- TEHLİKE (Hazard): Zarar verme potansiyeli olan her şey (örn. elektrik, kimyasal, yüksekte çalışma).\n- RİSK (Risk): Zararın meydana gelme olasılığı ile şiddetinin bileşimidir.\n- RİSK PROFİLİ: Kuruluşun karşı karşıya olduğu tehditlerin genel resmidir.",
                        en = "Risk assessment is examining what could cause harm and deciding if we have done enough to prevent it.\n\nKey Definitions:\n- HAZARD: Something with potential to cause harm.\n- RISK: Likelihood x Severity.\n- RISK PROFILING: The overall picture of threats facing the organization.",
                        de = "Risikobeurteilung prüft Gefahren und Maßnahmen. Gefahr = Schadenspotenzial. Risiko = Wahrscheinlichkeit x Schwere.",
                        pl = "Ocena ryzyka bada zagrożenia i środki. Zagrożenie = Potencjał szkody. Ryzyko = Prawdopodobieństwo x Dotkliwość."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("Risk Değerlendirmesinin 5 Adımı", "5 Steps of Risk Assessment", "5 Schritte der Risikobeurteilung", "5 Kroków Oceny Ryzyka"),
                    content = LocalizedText(
                        tr = "HSE (İngiltere İSG Kurumu) 5 adımlı bir yaklaşım önerir:\n\n1. Tehlikeleri belirle (Identify Hazards).\n2. Kimin ve nasıl zarar görebileceğini belirle (Who might be harmed).\n3. Riskleri değerlendir ve önlemlere karar ver (Evaluate & Decide).\n4. Bulguları kaydet ve uygula (Record).\n5. Değerlendirmeyi gözden geçir ve gerekirse güncelle (Review).",
                        en = "HSE recommends 5 steps:\n1. Identify hazards.\n2. Identify who might be harmed.\n3. Evaluate risk & decide on precautions.\n4. Record findings.\n5. Review and update.",
                        de = "5 Schritte: 1. Gefahren erkennen, 2. Wer ist betroffen?, 3. Bewerten, 4. Aufzeichnen, 5. Überprüfen.",
                        pl = "5 kroków: 1. Zidentyfikuj zagrożenia, 2. Kto ucierpi?, 3. Oceń, 4. Zapisz, 5. Przegląd."
                    ),
                    imageType = "pdca"
                ),
                LessonSection(
                    title = LocalizedText("Adım 1 ve 2: Tehlikeler ve İnsanlar", "Step 1 & 2: Hazards & People", "Schritt 1 & 2", "Krok 1 i 2"),
                    content = LocalizedText(
                        tr = "ADIM 1: Tehlikeleri nasıl buluruz?\n- İşyerini dolaşarak (Gözlem).\n- Kaza kayıtlarını inceleyerek.\n- Çalışanlara sorarak.\n- Üretici talimatlarını okuyarak.\n\nADIM 2: Kimler risk altında?\nSadece ofis çalışanları değil! Temizlikçiler, bakımcılar, ziyaretçiler, müteahhitler ve halk da düşünülmelidir. Özellikle 'Hassas Gruplar'a dikkat edilmelidir.",
                        en = "STEP 1: Find hazards by walking around, checking records, asking staff.\n\nSTEP 2: Who is at risk? Consider cleaners, maintenance, visitors, public. Pay attention to 'Vulnerable Groups'.",
                        de = "Gefahren durch Rundgang finden. An alle denken: Putzkräfte, Besucher, Öffentlichkeit.",
                        pl = "Znajdź zagrożenia podczas obchodu. Pamiętaj o wszystkich: sprzątacze, goście, publiczność."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("Hassas Gruplar (Risk Altındakiler)", "Vulnerable Groups", "Gefährdete Gruppen", "Grupy Wrażliwe"),
                    content = LocalizedText(
                        tr = "Bazı çalışanlar diğerlerinden daha fazla risk altındadır ve özel koruma gerektirir:\n\n- GENÇ İŞÇİLER: Deneyimsizdirler, tehlikeyi algılayamazlar, olgun değildirler.\n- HAMİLE KADINLAR: Bazı kimyasallar, ağır kaldırma ve titreşim bebeğe zarar verebilir.\n- ENGELLİ ÇALIŞANLAR: Acil durumda tahliye zorluğu yaşayabilirler.\n- YALNIZ ÇALIŞANLAR: Yardım çağıramazlar.",
                        en = "Specific groups need extra protection:\n- YOUNG WORKERS: Inexperienced, immature.\n- PREGNANT WOMEN: Chemicals, lifting can harm the baby.\n- DISABLED WORKERS: Evacuation issues.\n- LONE WORKERS: Cannot call for help.",
                        de = "Junge Arbeiter (unerfahren), Schwangere, Behinderte, Alleinarbeiter.",
                        pl = "Młodzi pracownicy (niedoświadczeni), Kobiety w ciąży, Niepełnosprawni, Pracujący samotnie."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("Adım 3: Risk Matrisi", "Step 3: Risk Matrix", "Schritt 3: Risikomatrix", "Krok 3: Matryca Ryzyka"),
                    content = LocalizedText(
                        tr = "Riski değerlendirmek için 'Yarı Kantitatif' bir yöntem kullanabiliriz:\n\nRisk = Olasılık x Şiddet\n\nÖrnek Puanlama:\n- Olasılık: 1 (Çok düşük) ile 5 (Çok yüksek) arası.\n- Şiddet: 1 (Küçük sıyrık) ile 5 (Ölüm) arası.\n\nEğer Olasılık 3 ve Şiddet 4 ise, Risk Puanı = 12'dir. Puan ne kadar yüksekse, o kadar acil önlem alınmalıdır.",
                        en = "Risk = Likelihood x Severity.\n\nExample:\n- Likelihood: 1-5\n- Severity: 1-5\n\nIf Likelihood is 3 and Severity is 4, Risk Score = 12. Higher score = higher priority.",
                        de = "Risiko = Wahrscheinlichkeit x Schwere. Matrix hilft bei der Priorisierung.",
                        pl = "Ryzyko = Prawdopodobieństwo x Dotkliwość. Matryca pomaga ustalić priorytety."
                    ),
                    imageType = "audit"
                )
            )
        )
        neboshDao.insertTopic(Topic("3.4", "IG1", "Risk Değerlendirmesi", "Risk Assessment", "Risikobeurteilung", "Ocena Ryzyka", Gson().toJson(lesson3_4)))
    }

    private suspend fun insertElement3Topic5() {
        val lesson3_5 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Değişim Neden Yönetilmeli?", "Why Manage Change?", "Warum Wandel managen?", "Dlaczego zarządzać zmianą?"),
                    content = LocalizedText(
                        tr = "İşyerleri sürekli değişir. Değişim, yeni tehlikeler getirir ve mevcut kontrolleri geçersiz kılabilir. Kazaların büyük bir kısmı değişim dönemlerinde (örneğin bakım çalışmaları, inşaat veya yeni makine kurulumu sırasında) meydana gelir.\n\nEğer değişim proaktif olarak yönetilmezse, riskler kontrolsüz kalır. Bu nedenle risk değerlendirmesi, değişiklik gerçekleşmeden ÖNCE yapılmalıdır.",
                        en = "Workplaces change constantly. Change brings new hazards. Many accidents happen during change (e.g., maintenance, construction).\n\nIf not managed proactively, risks remain uncontrolled. Risk assessment must be done BEFORE the change happens.",
                        de = "Wandel bringt neue Gefahren. Risikobeurteilung muss VOR der Änderung erfolgen.",
                        pl = "Zmiana wprowadza nowe zagrożenia. Ocena ryzyka musi być przeprowadzona PRZED zmianą."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("Tipik Değişiklik Türleri", "Types of Change", "Arten von Änderungen", "Rodzaje Zmian"),
                    content = LocalizedText(
                        tr = "İSG'yi etkileyen tipik değişiklikler şunlardır:\n\n1. GEÇİCİ İŞLER (Temporary Works): İnşaat, bakım, bina tadilatı. Kısa sürelidir ama yüksek risk taşır.\n2. SÜREÇ DEĞİŞİKLİĞİ: Üretim yönteminin değişmesi, yeni kimyasalların kullanılması.\n3. EKİPMAN DEĞİŞİKLİĞİ: Yeni makineler (otomasyon) veya el aletleri.\n4. PERSONEL DEĞİŞİKLİĞİ: Yeni yöneticiler, yeniden yapılanma, küçülme (downsizing).\n5. YASAL DEĞİŞİKLİKLER: Yeni kanunlar ve standartlar.",
                        en = "Typical changes impacting H&S:\n1. TEMPORARY WORKS: Construction, maintenance.\n2. PROCESS: New methods/chemicals.\n3. EQUIPMENT: New machinery.\n4. PERSONNEL: Restructuring, downsizing.\n5. LEGAL: New laws.",
                        de = "Vorübergehende Arbeiten, Prozessänderungen, Ausrüstung, Personal, Gesetze.",
                        pl = "Prace tymczasowe, Procesy, Sprzęt, Personel, Prawo."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("Değişim Yönetim Süreci", "Management Process", "Managementprozess", "Proces Zarządzania"),
                    content = LocalizedText(
                        tr = "Değişimi güvenli bir şekilde yönetmek için şu adımlar izlenmelidir:\n\n1. RİSK DEĞERLENDİRMESİ: Değişiklikten önce yapılmalı.\n2. İLETİŞİM VE DANIŞMA: Çalışanlar değişikliğin nedenini ve nasıl yapılacağını bilmelidir.\n3. YETKİN PERSONEL ATAMA: İşi yapacak kişiler (örn. müteahhitler) yetkin olmalı.\n4. EĞİTİM: Yeni ekipman veya süreç için eğitim verilmeli.\n5. İZLEME: Değişiklik sonrası her şeyin yolunda gittiği (örn. yeni makinenin güvenli olduğu) kontrol edilmeli.",
                        en = "Steps to manage change safely:\n1. RISK ASSESSMENT (Before change).\n2. COMMUNICATION (Consult workers).\n3. APPOINT COMPETENT PEOPLE.\n4. TRAINING.\n5. MONITORING (Check post-change safety).",
                        de = "Risikobeurteilung, Kommunikation, Kompetenz, Training, Überwachung.",
                        pl = "Ocena ryzyka, Komunikacja, Kompetencje, Szkolenie, Monitorowanie."
                    ),
                    imageType = "pdca"
                ),
                LessonSection(
                    title = LocalizedText("Örnek: Yeni Makine Alımı", "Example: New Machinery", "Beispiel: Neue Maschine", "Przykład: Nowa Maszyna"),
                    content = LocalizedText(
                        tr = "Bir fabrika yeni bir robotik hat kuruyor. Ne yapılmalı?\n\n- Risk Değerlendirmesi: Hareketli parçalar, elektrik riski.\n- Eğitim: Operatörlere ve bakımcılara eğitim verilmeli.\n- Prosedürler: Yeni 'Güvenli Çalışma Sistemleri' (SSoW) yazılmalı.\n- İletişim: Gürültü artacak mı? KKD ihtiyacı değişecek mi?\n- Acil Durum: Acil durdurma düğmeleri nerede?",
                        en = "Example: Installing a robotic line.\n- Risk Assessment: Moving parts.\n- Training: For operators.\n- Procedures: New SSoW.\n- Communication: Noise/PPE changes.\n- Emergency: Where are E-Stops?",
                        de = "Beispiel neue Maschine: Risiko, Training, Prozeduren, Notfall.",
                        pl = "Przykład nowej maszyny: Ryzyko, Szkolenie, Procedury, Awaria."
                    ),
                    imageType = "audit"
                )
            )
        )
        neboshDao.insertTopic(Topic("3.5", "IG1", "Değişim Yönetimi", "Management of Change", "Management des Wandels", "Zarządzanie Zmianą", Gson().toJson(lesson3_5)))
    }

    private suspend fun insertElement3Topic6() {
        val lesson3_6 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Güvenli Çalışma Sistemi (SSoW) Nedir?", "What is SSoW?", "Was ist SSoW?", "Co to jest SSoW?"),
                    content = LocalizedText(
                        tr = "Güvenli Çalışma Sistemi (SSoW), bir işin tehlikelerini ve risklerini dikkate alarak, o işin güvenli bir şekilde yapılmasını sağlayan resmi bir prosedürdür.\n\nSadece 'dikkatli ol' demek yetmez. İşin ADIM ADIM nasıl yapılacağı yazılı olarak belirlenmelidir.\n\nSSoW oluşturulurken 4 unsur dikkate alınır (PEME):\n1. İnsanlar (People)\n2. Ekipman (Equipment)\n3. Malzemeler (Materials)\n4. Çevre (Environment)",
                        en = "A Safe System of Work (SSoW) is a formal procedure for carrying out work safely, considering hazards and risks.\n\nIt defines STEP-BY-STEP how to do the job.\n\n4 Elements (PEME):\n1. People\n2. Equipment\n3. Materials\n4. Environment",
                        de = "SSoW ist ein formelles Verfahren. 4 Elemente: Menschen, Ausrüstung, Materialien, Umwelt.",
                        pl = "SSoW to formalna procedura. 4 Elementy: Ludzie, Sprzęt, Materiały, Środowisko."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("SSoW Geliştirme Adımları", "Developing a SSoW", "Entwicklung eines SSoW", "Opracowanie SSoW"),
                    content = LocalizedText(
                        tr = "Güvenli bir sistem oluşturmak için 5 adım izlenir:\n\n1. GÖREVİ ANALİZ ET: İş ne? Kim yapıyor? Hangi ekipmanlar kullanılıyor?\n2. TEHLİKELERİ BELİRLE: Risk değerlendirmesi yap.\n3. GÜVENLİ YÖNTEMİ TANIMLA: Riskleri nasıl kontrol edeceğiz? (Örn: Makineyi kilitle, KKD kullan).\n4. SİSTEMİ UYGULA: Çalışanları eğit ve prosedürü yayınla.\n5. İZLE: Sistemin çalıştığından emin ol (Denetim).",
                        en = "5 Steps to create a SSoW:\n1. ANALYSE TASK: What, who, how?\n2. IDENTIFY HAZARDS: Risk assessment.\n3. DEFINE METHOD: How to control risks? (LOTO, PPE).\n4. IMPLEMENT: Train workers.\n5. MONITOR: Check if it works.",
                        de = "Aufgabe analysieren, Gefahren erkennen, Methode definieren, Umsetzen, Überwachen.",
                        pl = "Analiza zadania, Identyfikacja zagrożeń, Definicja metody, Wdrożenie, Monitorowanie."
                    ),
                    imageType = "pdca"
                ),
                LessonSection(
                    title = LocalizedText("Çalışma İzni (Permit-to-Work)", "Permit-to-Work (PTW)", "Arbeitserlaubnis", "Pozwolenie na Pracę"),
                    content = LocalizedText(
                        tr = "Çalışma İzni (PTW), ÇOK YÜKSEK RİSKLİ işler için kullanılan, daha katı ve yazılı bir SSoW türüdür. İzin belgesi olmadan işe başlanamaz.\n\nPTW Gerektiren İşler:\n- Sıcak Çalışma (Kaynak, kesme - Yangın riski).\n- Kapalı Alanlarda Çalışma (Zehirli gaz, oksijen eksikliği).\n- Yüksekte Çalışma (Çatı onarımı).\n- Yüksek Gerilim Elektrik İşleri.\n- Kazı İşleri (Yeraltı kabloları).",
                        en = "Permit-to-Work (PTW) is a formal, written system for HIGH RISK activities.\n\nJobs requiring PTW:\n- Hot Work (Welding).\n- Confined Spaces.\n- Work at Height.\n- High Voltage Electrical.\n- Excavations.",
                        de = "PTW für Hochrisikoarbeiten: Heißarbeiten, Enge Räume, Höhe, Elektrik.",
                        pl = "PTW dla prac wysokiego ryzyka: Prace gorące, Przestrzenie zamknięte, Praca na wysokości."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("İzin Belgesinin Bölümleri", "Sections of a Permit", "Abschnitte einer Erlaubnis", "Sekcje Pozwolenia"),
                    content = LocalizedText(
                        tr = "Bir Çalışma İzni belgesi 4 ana bölümden oluşur:\n\n1. VERİLİŞ (Issue): Yetkili kişi işi tanımlar, tehlikeleri ve alınacak önlemleri (izolasyon, KKD) belirtir ve imzalar.\n2. KABUL (Receipt): İşi yapacak kişi önlemleri anladığını beyan eder ve imzalar.\n3. ELDEN GEÇİRME/DEVRETME (Hand-back): İş bitince veya mola verilince durum kontrol edilir.\n4. İPTAL (Cancellation): İş tamamlandığında izin kapatılır ve sistem normale döner.",
                        en = "4 Sections of a Permit:\n1. ISSUE: Authorised person defines work & precautions.\n2. RECEIPT: Worker accepts conditions.\n3. HAND-BACK: Check status after work.\n4. CANCELLATION: Permit closed, system back to normal.",
                        de = "Ausgabe, Empfang, Rückgabe, Stornierung.",
                        pl = "Wydanie, Odbiór, Zwrot, Anulowanie."
                    ),
                    imageType = "audit"
                )
            )
        )
        neboshDao.insertTopic(Topic("3.6", "IG1", "Güvenli Çalışma Sistemleri", "Safe Systems of Work", "Sichere Arbeitssysteme", "Bezpieczne Systemy Pracy", Gson().toJson(lesson3_6)))
    }

    private suspend fun insertElement3Topic7() {
        val lesson3_7 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Acil Durum Prosedürleri", "Emergency Procedures", "Notfallverfahren", "Procedury Awaryjne"),
                    content = LocalizedText(
                        tr = "İşveren, öngörülebilir acil durumlar için plan yapmalıdır. Sadece yangın değil, şunlar da düşünülmelidir:\n\n- Bomba ihbarı / Terör saldırısı.\n- Kimyasal sızıntı (Dökülme).\n- Şiddetli hava koşulları (Sel, fırtına).\n- Ciddi kaza / Çoklu yaralanma.\n\nPlan şunları içermelidir: Alarmın nasıl verileceği, tahliye yolları, toplanma noktaları ve özel görevli personel (Yangın görevlileri).",
                        en = "Employers must plan for foreseeable emergencies. Not just fire, but also:\n- Bomb threats.\n- Chemical spills.\n- Severe weather.\n- Major accidents.\n\nThe plan must cover: Raising the alarm, evacuation routes, assembly points, and responsible personnel.",
                        de = "Planung für Notfälle: Feuer, Bomben, Chemikalien, Wetter. Alarm, Evakuierung.",
                        pl = "Planowanie na wypadek awarii: Pożar, Bomba, Chemikalia, Pogoda. Alarm, Ewakuacja."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("İlk Yardım: İşveren Yükümlülüğü", "First Aid Requirements", "Erste-Hilfe-Anforderungen", "Wymogi Pierwszej Pomocy"),
                    content = LocalizedText(
                        tr = "İşveren, bir kaza anında çalışanlara anında müdahale edilebilmesi için 3 şeyi sağlamalıdır:\n\n1. TESİSLER (Facilities): İlk yardım odası veya uygun bir alan.\n2. EKİPMAN (Equipment): İlk yardım kutusu (yeterli stoklu), göz yıkama duşu vb.\n3. PERSONEL (Personnel): Eğitimli ilk yardımcılar.\n\nİlk yardımın 3 amacı vardır (3P): Yaşamı koru (Preserve), Durumun kötüleşmesini önle (Prevent), İyileşmeyi hızlandır (Promote).",
                        en = "Employers must provide 3 things:\n1. FACILITIES (Room).\n2. EQUIPMENT (Box).\n3. PERSONNEL (First Aiders).\n\n3 Ps of First Aid: Preserve life, Prevent deterioration, Promote recovery.",
                        de = "Arbeitgeber muss Einrichtungen, Ausrüstung und Personal bereitstellen. 3 Ps: Leben bewahren, Verschlechterung verhindern, Erholung fördern.",
                        pl = "Pracodawca musi zapewnić obiekty, sprzęt i personel. 3 P: Chronić życie, Zapobiegać pogorszeniu, Wspierać powrót do zdrowia."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("İlk Yardımcı vs Atanmış Kişi", "First Aider vs Appointed Person", "Ersthelfer vs. Beauftragte Person", "Ratownik vs Osoba Wyznaczona"),
                    content = LocalizedText(
                        tr = "İşyerindeki risk seviyesine göre personel seçilir:\n\n1. İLK YARDIMCI (First Aider): Sertifikalı eğitim almış, müdahale yetkisi olan kişidir (Yüksek riskli veya kalabalık yerler için).\n\n2. ATANMIŞ KİŞİ (Appointed Person): Resmi eğitimi yoktur. Görevi sadece ambulans çağırmak ve ilk yardım kutusunu dolu tutmaktır (Düşük riskli, küçük ofisler için).\n\nPersonel sayısı şunlara göre belirlenir: İşçi sayısı, tehlikeler, vardiya sistemi, en yakın hastaneye uzaklık.",
                        en = "1. FIRST AIDER: Certified/Trained. Can treat injuries (High risk).\n2. APPOINTED PERSON: No formal training. Calls ambulance, manages box (Low risk).\n\nNumber depends on: Staff count, hazards, shifts, distance to hospital.",
                        de = "Ersthelfer (Ausgebildet) vs. Beauftragte Person (Nur Notruf/Material).",
                        pl = "Ratownik (Przeszkolony) vs. Osoba Wyznaczona (Tylko ambulans/sprzęt)."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("Kutu ve İlk Yardım Odası", "The Box & The Room", "Kasten & Raum", "Apteczka & Pokój"),
                    content = LocalizedText(
                        tr = "İLK YARDIM KUTUSU: Yeşil üzerine beyaz haç işaretli olmalıdır. İçinde yara bandı, sargı bezi, eldiven bulunmalıdır. İlaç (ağrı kesici vb.) BULUNMAMALIDIR (alerji riski).\n\nİLK YARDIM ODASI: Şu durumlarda gereklidir:\n- Yüksek riskli işler (örn. tersane, kimya fabrikası).\n- Hastaneye uzak konumlar.\n- Çok sayıda çalışan varsa.\nOda temiz olmalı, lavabo bulunmalı ve sedye girebilecek kadar geniş olmalıdır.",
                        en = "THE BOX: Green/White cross. Bandages, gloves. NO MEDICATION (pills).\nTHE ROOM: Needed for high risk or remote sites. Must be clean, have a sink, and fit a stretcher.",
                        de = "Kasten: Keine Medikamente! Raum: Für Hochrisiko oder abgelegene Orte.",
                        pl = "Apteczka: Żadnych leków! Pokój: Dla wysokiego ryzyka lub odległych miejsc."
                    ),
                    imageType = "hazard"
                )
            )
        )
        neboshDao.insertTopic(Topic("3.7", "IG1", "Acil Durum ve İlk Yardım", "Emergency & First Aid", "Notfall & Erste Hilfe", "Nagłe Wypadki i Pierwsza Pomoc", Gson().toJson(lesson3_7)))
    }

    // --- ELEMENT 4 ---
    private suspend fun insertElement4Topics() {
        val lesson4_1 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Aktif ve Reaktif İzleme Nedir?", "Active vs Reactive Monitoring", "Aktive vs. Reaktive Überwachung", "Monitorowanie Aktywne vs Reaktywne"),
                    content = LocalizedText(
                        tr = "İSG performansını ölçmenin iki yolu vardır:\n\n1. AKTİF İZLEME (Öncü Göstergeler): İstenmeyen bir olay (kaza) gerçekleşmeden ÖNCE standartları kontrol etmektir. Geleceği tahmin eder.\n2. REAKTİF İZLEME (Gecikmeli Göstergeler): Kazalar, hastalıklar ve olaylar gerçekleştikten SONRA ölçüm yapmaktır. Geçmişteki başarısızlıkları gösterir.\n\nSadece reaktif izleme yapmak, arabayı sadece dikiz aynasına bakarak sürmeye benzer.",
                        en = "1. ACTIVE (Leading Indicators): Checking standards BEFORE an event happens. Predicts the future.\n2. REACTIVE (Lagging Indicators): Measuring AFTER accidents/ill-health occur. Shows past failures.\nRelying only on reactive is like driving looking only in the rearview mirror.",
                        de = "Aktiv (Vor dem Ereignis/Führend) vs. Reaktiv (Nach dem Ereignis/Verzögert).",
                        pl = "Aktywne (Przed zdarzeniem/Wskaźniki wyprzedzające) vs. Reaktywne (Po zdarzeniu/Wskaźniki opóźnione)."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("Aktif İzleme Yöntemleri", "Active Monitoring Methods", "Methoden der aktiven Überwachung", "Metody Monitorowania Aktywnego"),
                    content = LocalizedText(
                        tr = "Standartlara uyumu kontrol etmek için 3 ana yöntem vardır:\n\n1. GÜVENLİK TEFTİŞLERİ (Inspections): İşyerinin fiziksel durumunun detaylı incelenmesidir.\n2. GÜVENLİK ÖRNEKLEMESİ (Sampling): Belirli bir alanın veya ekipmanın sadece bir kısmını (örneğin rastgele 50 yangın söndürücüyü) kontrol etmektir.\n3. GÜVENLİK TURLARI (Tours): Yöneticilerin yaptığı, daha genel ve yüksek profilli (görünür) bir gezintidir. Amaç liderlik göstermek ve işçilerle konuşmaktır.",
                        en = "1. INSPECTIONS: Detailed check of physical conditions.\n2. SAMPLING: Checking a representative sample (e.g., random 50 extinguishers).\n3. TOURS: High-profile walkaround by managers. Focus on leadership and morale.",
                        de = "Inspektionen (Detailliert), Stichproben (Teilweise), Touren (Management/Führung).",
                        pl = "Inspekcje (Szczegółowe), Próbkowanie (Częściowe), Obchody (Zarządzanie/Przywództwo)."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("Teftişlerde 4P Kuralı", "The 4 Ps of Inspection", "Die 4 Ps der Inspektion", "Zasada 4P w Inspekcji"),
                    content = LocalizedText(
                        tr = "Sistematik bir teftiş genellikle '4P' üzerine odaklanır:\n\n1. PLANT (Fabrika/Makine): Makineler, araçlar, ekipmanlar güvenli mi?\n2. PREMISES (Tesisler): İşyeri ortamı (zemin, aydınlatma, havalandırma, çıkışlar) uygun mu?\n3. PEOPLE (İnsanlar): Çalışanların davranışları, KKD kullanımları ve çalışma yöntemleri güvenli mi?\n4. PROCEDURES (Prosedürler): İzinler, kurallar ve tabelalar yerinde mi?",
                        en = "Systematic inspections focus on the 4 Ps:\n1. PLANT: Machinery, equipment, vehicles.\n2. PREMISES: Workplace environment (floors, lights, exits).\n3. PEOPLE: Behaviour, PPE usage.\n4. PROCEDURES: Permits, rules, signs.",
                        de = "4 Ps: Plant (Anlagen), Premises (Räumlichkeiten), People (Menschen), Procedures (Verfahren).",
                        pl = "4 P: Plant (Fabryka), Premises (Obiekty), People (Ludzie), Procedures (Procedury)."
                    ),
                    imageType = "hazard"
                ),
                LessonSection(
                    title = LocalizedText("Reaktif Veri Kaynakları", "Reactive Data Sources", "Reaktive Datenquellen", "Źródła Danych Reaktywnych"),
                    content = LocalizedText(
                        tr = "Reaktif izleme, başarısızlıkları analiz ederek ders çıkarmayı sağlar. Veri kaynakları:\n\n- Kaza İstatistikleri (Yaralanma ve hasar).\n- Ramak Kala (Near Miss) raporları (Çok değerlidir çünkü potansiyeli gösterir).\n- Meslek Hastalıkları (Ill-health) verileri.\n- Çalışan Şikayetleri.\n- Sigorta Tazminat Talepleri.\n- Yasal Yaptırımlar (Müfettiş cezaları).",
                        en = "Reactive monitoring learns from failures. Data sources:\n- Accident stats.\n- Near miss reports (Very valuable).\n- Ill-health data.\n- Complaints.\n- Insurance claims.\n- Enforcement actions.",
                        de = "Datenquellen: Unfälle, Beinaheunfälle, Krankheiten, Beschwerden, Ansprüche.",
                        pl = "Źródła danych: Wypadki, Zdarzenia potencjalne, Choroby, Skargi, Roszczenia."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("İstatistik: Kaza Sıklık Oranı", "Accident Frequency Rate (AFR)", "Unfallhäufigkeitsrate", "Wskaźnik Częstotliwości Wypadków"),
                    content = LocalizedText(
                        tr = "Performansı karşılaştırmak için ham sayılar yerine 'Oranlar' kullanılır. En yaygın olanı 'Zaman Kayıplı Kaza Sıklık Oranı'dır:\n\nFormül:\n(Zaman Kayıplı Kaza Sayısı / Toplam Çalışılan Saat) x 100.000\n\nBu formül, çalışan sayısı değişse bile yıllar arasında adil bir kıyaslama yapmayı sağlar.",
                        en = "To compare performance, we use rates. Common formula (Lost-time AFR):\n\n(Number of lost-time accidents / Total hours worked) x 100,000\n\nThis allows comparison even if staff numbers change.",
                        de = "Formel: (Unfälle mit Ausfallzeit / Geleistete Arbeitsstunden) x 100.000.",
                        pl = "Wzór: (Liczba wypadków z czasem straconym / Przepracowane godziny) x 100 000."
                    ),
                    imageType = "pdca"
                )
            )
        )
        neboshDao.insertTopic(Topic("4.1", "IG1", "Aktif ve Reaktif İzleme", "Active & Reactive Monitoring", "Aktive & Reaktive Überwachung", "Monitorowanie", Gson().toJson(lesson4_1)))
    }

    private suspend fun insertElement4Topic2() {
        val lesson4_2 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Neden Araştırmalıyız?", "Why Investigate?", "Warum untersuchen?", "Dlaczego badać?"),
                    content = LocalizedText(
                        tr = "Olayları araştırmanın temel amacı suçluyu bulmak değil, 'Tekrarlanmasını Önlemektir' (Kök nedeni bulmak). Diğer nedenler:\n\n- Yasal gereklilikleri karşılamak.\n- Tazminat talepleri için bilgi toplamak (Sigorta).\n- Personel moralini korumak (Yönetimin ilgilendiğini gösterir).\n- Kaza verileri ve trendleri oluşturmak.\n- Risk değerlendirmelerini güncellemek.",
                        en = "The main reason is NOT to blame, but to PREVENT RECURRENCE (Find root cause). Other reasons:\n- Legal compliance.\n- Insurance claims data.\n- Staff morale (Shows management cares).\n- Data & Trends.\n- Update risk assessments.",
                        de = "Hauptgrund: Wiederholung verhindern. Rechtliche Gründe, Versicherung, Moral.",
                        pl = "Główny powód: Zapobieganie nawrotom. Zgodność z prawem, Roszczenia, Morale."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("Olay Türleri", "Types of Incidents", "Art der Vorfälle", "Rodzaje Zdarzeń"),
                    content = LocalizedText(
                        tr = "1. KAZA (Accident): Yaralanma veya hasarla sonuçlanan planlanmamış olay.\n2. RAMAK KALA (Near Miss): Yaralanma veya hasar potansiyeli olan ama gerçekleşmeyen olay (Şans eseri kurtulma). Bunları araştırmak çok önemlidir çünkü bir sonraki sefere kaza olabilir.\n3. TEHLİKELİ OLAY (Dangerous Occurrence): Yaralanma olmasa bile yasal olarak bildirilmesi gereken olaylar (örn. vincin devrilmesi).\n4. MESLEK HASTALIĞI: İşin neden olduğu sağlık sorunları (örn. asbest, gürültü).",
                        en = "1. ACCIDENT: Injury/Damage occurs.\n2. NEAR MISS: Potential for harm, but none occurred. Must investigate as next time could be fatal.\n3. DANGEROUS OCCURRENCE: Reportable event (e.g. crane collapse) even if no injury.\n4. ILL-HEALTH: Disease caused by work.",
                        de = "Unfall, Beinaheunfall (Wichtig!), Gefährliches Vorkommnis, Berufskrankheit.",
                        pl = "Wypadek, Zdarzenie potencjalne (Ważne!), Niebezpieczne zdarzenie, Choroba zawodowa."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("Adım 1 ve 2: Bilgi ve Analiz", "Step 1 & 2: Gather & Analyse", "Schritt 1 & 2", "Krok 1 i 2"),
                    content = LocalizedText(
                        tr = "ADIM 1: BİLGİ TOPLA\n- Fiziksel Kanıtlar (Fotoğraf, ölçüm, CCTV).\n- Tanık Görüşmeleri (Hemen yapılmalı, suçlayıcı olmamalı).\n- Belgeler (Risk değerlendirmesi, eğitim kayıtları, bakım raporları).\n\nADIM 2: ANALİZ ET (NEDENLER)\n- Doğrudan Neden (Immediate): Olayın kendisi (örn. yere dökülen yağ, KKD eksikliği).\n- Kök Neden (Root): Arkadaki yönetimsel hata (örn. bakım eksikliği, yetersiz denetim, eğitim verilmemesi).",
                        en = "STEP 1: GATHER INFO (Photos, Witnesses, Documents).\nSTEP 2: ANALYSE\n- Immediate Cause: Unsafe act/condition (e.g. oil slip).\n- Root Cause: Management failure (e.g. no maintenance, poor supervision). Always fix the Root Cause!",
                        de = "1. Info sammeln (Fotos, Zeugen). 2. Analyse (Unmittelbare Ursache vs. Grundursache).",
                        pl = "1. Zbierz informacje (Zdjęcia, Świadkowie). 2. Analiza (Przyczyna bezpośrednia vs Źródłowa)."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("Adım 3 ve 4: Kontrol ve Plan", "Step 3 & 4: Control & Plan", "Schritt 3 & 4", "Krok 3 i 4"),
                    content = LocalizedText(
                        tr = "ADIM 3: KONTROL ÖNLEMLERİ\nSadece doğrudan nedeni (yerdeki yağı temizle) değil, kök nedeni (makine bakımını yap) çözecek önlemler belirle. Hiyerarşiyi kullan (Elimine et -> KKD).\n\nADIM 4: EYLEM PLANI\nÖnlemleri hayata geçir. Eylem planında şunlar olmalı:\n- Ne yapılacak?\n- Kim yapacak? (Sorumlu)\n- Ne zaman yapılacak? (Termin)\n- Öncelik sırası.",
                        en = "STEP 3: IDENTIFY CONTROLS\nFix Root Causes, not just immediate ones. Use Hierarchy of Control.\n\nSTEP 4: ACTION PLAN\n- What?\n- Who (Responsible)?\n- When (Deadline)?\n- Priority.",
                        de = "3. Maßnahmen (Grundursachen beheben). 4. Aktionsplan (Wer, Wann, Was).",
                        pl = "3. Środki kontroli (Napraw przyczyny źródłowe). 4. Plan działania (Kto, Kiedy, Co)."
                    ),
                    imageType = "pdca"
                ),
                LessonSection(
                    title = LocalizedText("Raporlama ve Kayıt Tutma", "Reporting & Recording", "Berichterstattung", "Raportowanie"),
                    content = LocalizedText(
                        tr = "1. İÇ RAPORLAMA: Çalışanlar her olayı (ramak kalalar dahil) bildirmelidir. Engeller: Korku, karmaşık formlar, yönetimin ilgisizliği.\n2. KAYIT: Kaza Defteri (Yasal zorunluluk). İsim, tarih, yer, yaralanma detayları.\n3. DIŞ RAPORLAMA (Yasal): Ciddi olaylar devlete bildirilmelidir (örn. İngiltere'de RIDDOR):\n- Ölüm.\n- Büyük Yaralanmalar (Kırık, amputasyon).\n- 7 günden fazla iş göremezlik.\n- Meslek Hastalıkları.",
                        en = "1. INTERNAL: Workers must report all (inc. near miss). Barriers: Fear, apathy.\n2. RECORDING: Accident Book (Name, date, injury).\n3. EXTERNAL (Legal/RIDDOR): Must report Fatalities, Major Injuries, >7 Day incapacitation, Diseases.",
                        de = "Intern (Alle Vorfälle), Aufzeichnung (Unfallbuch), Extern (Meldepflichtige Vorfälle: Tod, schwere Verletzung).",
                        pl = "Wewnętrzne (Wszystkie), Rejestracja (Księga wypadków), Zewnętrzne (Zgony, Poważne urazy)."
                    ),
                    imageType = "hazard"
                )
            )
        )
        neboshDao.insertTopic(Topic("4.2", "IG1", "Olayların Araştırılması", "Incident Investigation", "Unfalluntersuchung", "Badanie Wypadków", Gson().toJson(lesson4_2)))
    }

    private suspend fun insertElement4Topic3() {
        val lesson4_3 = TopicContent(
            sections = listOf(
                LessonSection(
                    title = LocalizedText("Denetim (Audit) Nedir?", "What is Auditing?", "Was ist Auditierung?", "Co to jest Audyt?"),
                    content = LocalizedText(
                        tr = "Denetim, bir kurumun İSG yönetim sisteminin sistematik, objektif ve kritik bir değerlendirmesidir. Amaç, sistemin (kağıt üzerindeki kuralların) gerçekte çalışıp çalışmadığını doğrulamaktır.\n\n3 Anahtar Kelime:\n1. Sistematik: Planlıdır, rastgele değildir.\n2. Objektif: Kanıtlara dayanır, görüşlere değil.\n3. Kritik: Zayıflıkları bulur ve iyileştirme önerir.",
                        en = "Audit is a systematic, objective, and critical evaluation of the OHSMS. It verifies if the system works.\n\n3 Key Words:\n1. Systematic: Planned.\n2. Objective: Evidence-based.\n3. Critical: Finds weaknesses.",
                        de = "Audit ist eine systematische, objektive und kritische Bewertung des Managementsystems.",
                        pl = "Audyt to systematyczna, obiektywna i krytyczna ocena systemu zarządzania."
                    ),
                    imageType = "policy"
                ),
                LessonSection(
                    title = LocalizedText("Denetim ve Teftiş Farkı", "Audit vs Inspection", "Audit vs. Inspektion", "Audyt vs Inspekcja"),
                    content = LocalizedText(
                        tr = "Bu ikisi sınavda çok karıştırılır!\n\n- TEFTİŞ (Inspection): 'Şu anki duruma' bakar. Fiziksel tehlikeleri (kırık korkuluk, dökülmüş yağ) kontrol eder. Basittir, sık yapılır.\n\n- DENETİM (Audit): 'Sisteme' bakar. Neden korkuluk kırık? Bakım prosedürü mü yok? Eğitim mi eksik? Derinlemesine incelemedir, yılda bir yapılır.",
                        en = "Don't confuse them!\n- INSPECTION: Checks physical conditions (hazards) right now. Simple, frequent.\n- AUDIT: Checks the SYSTEM. Why is it broken? Reviews procedures and records. In-depth, infrequent.",
                        de = "Inspektion prüft Bedingungen (Gefahren). Audit prüft das System (Verfahren).",
                        pl = "Inspekcja sprawdza warunki (zagrożenia). Audyt sprawdza system (procedury)."
                    ),
                    imageType = "warning"
                ),
                LessonSection(
                    title = LocalizedText("Denetim Süreci (3 Aşama)", "The Audit Process", "Der Auditprozess", "Proces Audytu"),
                    content = LocalizedText(
                        tr = "1. ÖNCESİ (Pre-audit): Kapsam belirlenir, zaman planlanır, denetçiler seçilir.\n2. ESNASI (During): Bilgi toplanır. 3 yöntem kullanılır: Belgeleri incele, Mülakat yap (çalışanlarla konuş), Gözlem yap (sahayı gez).\n3. SONRASI (Post-audit): Rapor yazılır, uygunsuzluklar (Major/Minör) belirlenir ve düzeltici faaliyetler planlanır.",
                        en = "1. PRE: Define scope, select team.\n2. DURING: Gather info via Documents, Interviews, Observation.\n3. POST: Write report, identify non-conformities, plan corrective actions.",
                        de = "1. Vorbereitung. 2. Durchführung (Dokumente, Interviews, Beobachtung). 3. Nachbereitung (Bericht).",
                        pl = "1. Przed. 2. W trakcie (Dokumenty, Wywiady, Obserwacja). 3. Po (Raport)."
                    ),
                    imageType = "audit"
                ),
                LessonSection(
                    title = LocalizedText("İç ve Dış Denetim", "Internal vs External Audit", "Internes vs. Externes Audit", "Audyt Wewnętrzny vs Zewnętrzny"),
                    content = LocalizedText(
                        tr = "1. DIŞ DENETİM (Harici): Bağımsızdır, taze bir bakış açısı sunar, önyargısızdır. Ancak pahalıdır ve işletmeyi iyi tanımayabilirler.\n2. İÇ DENETİM (Dahili): Ucuzdur, işletmeyi iyi bilirler, çalışanlar daha rahattır. Ancak 'işletme körlüğü' olabilir ve eleştirmekten çekinebilirler.",
                        en = "1. EXTERNAL: Independent, fresh eyes, unbiased. But expensive.\n2. INTERNAL: Cheaper, knows the business. But may be biased or afraid to criticize managers.",
                        de = "Extern: Unabhängig aber teuer. Intern: Günstig aber evtl. voreingenommen.",
                        pl = "Zewnętrzny: Niezależny, ale drogi. Wewnętrzny: Tani, ale może być stronniczy."
                    ),
                    imageType = "pdca"
                )
            )
        )
        neboshDao.insertTopic(Topic("4.3", "IG1", "Denetim (Audit)", "Health & Safety Audit", "Sicherheitsaudit", "Audyt BHP", Gson().toJson(lesson4_3)))
    }
    // 3. TAM KAPSAMLI SINAV SORULARI (TÜM ALT KONULAR DOLU)
    private suspend fun insertSampleQuestions() {

        neboshDao.deleteAllQuestions()

        // --- ELEMENT 1 ---
        // 1.1
        neboshDao.insertQuestion(Question("q1_1", "1.1",
            LocalizedText("İSG'yi yönetmenin 3 ana nedeni nedir?", "3 main reasons to manage H&S?", "3 Hauptgründe?", "3 powody?"),
            Gson().toJson(listOf(LocalizedText("Ahlaki, Yasal, Finansal", "Moral, Legal, Financial", "Moralisch, Rechtlich, Finanziell", "Moralne, Prawne, Finansowe"), LocalizedText("Sosyal, Psikolojik, Fiziksel", "Social, Psychological, Physical", "Sozial...", "Społeczne..."), LocalizedText("Sigorta, Polis, Devlet", "Insurance, Police, State", "Versicherung...", "Ubezpieczenie..."), LocalizedText("Kâr, Üretim, Kalite", "Profit, Production, Quality", "Gewinn...", "Zysk..."))),
            0, LocalizedText("Temel nedenler Ahlaki, Yasal ve Finansal'dır.", "Moral, Legal, Financial.", "Moralisch, Rechtlich, Finanziell.", "Moralne, Prawne, Finansowe.")
        ))
        // 1.2
        neboshDao.insertQuestion(Question("q1_2", "1.2",
            LocalizedText("İşverenin yasal sorumluluklarını yerine getirmediği durumda hangi hukuk dalı devreye girer (Cezalandırma)?", "Which law punishes non-compliance?", "Welches Recht bestraft?", "Które prawo karze?"),
            Gson().toJson(listOf(LocalizedText("Medeni Hukuk", "Civil Law", "Zivilrecht", "Prawo Cywilne"), LocalizedText("Ceza Hukuku", "Criminal Law", "Strafrecht", "Prawo Karne"), LocalizedText("Sözleşme Hukuku", "Contract Law", "Vertragsrecht", "Prawo Umów"), LocalizedText("Aile Hukuku", "Family Law", "Familienrecht", "Prawo Rodzinne"))),
            1, LocalizedText("Ceza hukuku (Criminal Law) suç ve ceza ile ilgilenir.", "Criminal Law deals with punishment.", "Strafrecht.", "Prawo Karne.")
        ))
        // 1.3
        neboshDao.insertQuestion(Question("q1_3", "1.3",
            LocalizedText("İşyerinde nihai yasal sorumluluk kime aittir?", "Who has ultimate legal responsibility?", "Wer hat die Verantwortung?", "Kto ma odpowiedzialność?"),
            Gson().toJson(listOf(LocalizedText("İSG Uzmanı", "H&S Advisor", "Sicherheitsberater", "Doradca BHP"), LocalizedText("Formen", "Foreman", "Vorarbeiter", "Brygadzista"), LocalizedText("İşveren / CEO", "Employer / CEO", "Arbeitgeber", "Pracodawca"), LocalizedText("Çalışanlar", "Workers", "Arbeiter", "Pracownicy"))),
            2, LocalizedText("Yasal sorumluluk en üst düzeydedir (İşveren/CEO).", "Employer/CEO bears ultimate responsibility.", "Arbeitgeber.", "Pracodawca.")
        ))

        // --- ELEMENT 2 ---
        // 2.1
        neboshDao.insertQuestion(Question("q2_1", "2.1",
            LocalizedText("ISO 45001 hangi döngüye dayanır?", "ISO 45001 is based on?", "ISO 45001 basiert auf?", "ISO 45001 opiera się na?"),
            Gson().toJson(listOf(LocalizedText("Planla-Uygula-Kontrol Et-Önlem Al (PUKÖ)", "PDCA", "PDCA", "PDCA"), LocalizedText("Durdur-Düşün-Yap", "Stop-Think-Do", "Stopp-Denk-Tu", "Stop-Myśl-Rób"), LocalizedText("Risk-Analiz-Eylem", "Risk-Analysis-Action", "Risiko-Analyse", "Ryzyko-Analiza"), LocalizedText("Gözlemle-Raporla", "Observe-Report", "Beobachten", "Obserwuj"))),
            0, LocalizedText("PUKÖ (PDCA) döngüsü.", "PDCA Cycle.", "PDCA-Zyklus.", "Cykl PDCA.")
        ))
        // 2.2 (Politika)
        neboshDao.insertQuestion(Question("q2_2", "2.2",
            LocalizedText("İSG Politikasının 3 bölümü nedir?", "3 parts of H&S Policy?", "3 Teile der Politik?", "3 części polityki?"),
            Gson().toJson(listOf(LocalizedText("Giriş, Gelişme, Sonuç", "Intro, Body, Conclusion", "Einleitung...", "Wstęp..."), LocalizedText("Niyet Beyanı, Organizasyon, Düzenlemeler", "Statement, Org, Arrangements", "Erklärung, Org, Regelungen", "Oświadczenie, Org, Ustalenia"), LocalizedText("Plan, Do, Check", "Plan, Do, Check", "Plan, Do, Check", "Plan, Do, Check"), LocalizedText("Risk, Tehlike, Önlem", "Risk, Hazard, Control", "Risiko, Gefahr, Kontrolle", "Ryzyko, Zagrożenie, Kontrola"))),
            1, LocalizedText("Politika: Niyet (Ne), Organizasyon (Kim), Düzenlemeler (Nasıl).", "Statement (What), Org (Who), Arrangements (How).", "Erklärung, Organisation, Regelungen.", "Oświadczenie, Organizacja, Ustalenia.")
        ))

        // --- ELEMENT 3 ---
        // 3.1
        neboshDao.insertQuestion(Question("q3_1", "3.1",
            LocalizedText("Pozitif güvenlik kültürünün en iyi göstergesi nedir?", "Best indicator of positive culture?", "Bester Indikator?", "Najlepszy wskaźnik?"),
            Gson().toJson(listOf(LocalizedText("Düşük kaza oranı", "Low accident rate", "Niedrige Unfallrate", "Niski wskaźnik wypadków"), LocalizedText("Görünür liderlik", "Visible leadership", "Sichtbare Führung", "Widoczne przywództwo"), LocalizedText("Çok sayıda kural", "Many rules", "Viele Regeln", "Dużo zasad"), LocalizedText("Yüksek maaşlar", "High wages", "Hohe Löhne", "Wysokie płace"))),
            1, LocalizedText("Liderlerin sahada olması ve örnek olması en güçlü göstergedir.", "Visible leadership is key.", "Sichtbare Führung.", "Widoczne przywództwo.")
        ))
        // 3.2
        neboshDao.insertQuestion(Question("q3_2", "3.2",
            LocalizedText("İşe yeni başlayan birine verilen ilk eğitim hangisidir?", "First training for new starter?", "Erstes Training?", "Pierwsze szkolenie?"),
            Gson().toJson(listOf(LocalizedText("İşe Alıştırma (Induction)", "Induction", "Einführung", "Wdrożenie"), LocalizedText("İleri Yangın Söndürme", "Advanced Firefighting", "Brandbekämpfung", "Gaszenie pożarów"), LocalizedText("Yöneticilik Eğitimi", "Management Training", "Management", "Zarządzanie"), LocalizedText("İlk Yardım Sertifikası", "First Aid Cert", "Erste Hilfe", "Pierwsza Pomoc"))),
            0, LocalizedText("İşe alıştırma (Induction) eğitimi, acil durumları kapsar.", "Induction covers basics like emergency exits.", "Einführungstraining.", "Szkolenie wstępne.")
        ))
        // 3.3 (İnsan Faktörleri)
        neboshDao.insertQuestion(Question("q3_3", "3.3",
            LocalizedText("Bir operatörün düğmeye yanlışlıkla basması ne tür bir hatadır?", "Pressing wrong button unintentionally?", "Falscher Knopf (unbeabsichtigt)?", "Niewłaściwy przycisk (niezamierzenie)?"),
            Gson().toJson(listOf(LocalizedText("İhlal (Violation)", "Violation", "Verstoß", "Naruszenie"), LocalizedText("Sürçme (Slip)", "Slip", "Ausrutscher", "Poślizgnięcie"), LocalizedText("Hata (Mistake)", "Mistake", "Fehler", "Błąd"), LocalizedText("Sabotaj", "Sabotage", "Sabotage", "Sabotaż"))),
            1, LocalizedText("Eylem hatası 'Sürçme' (Slip) olarak adlandırılır.", "Action failure is a 'Slip'.", "Handlungsfehler ist ein 'Slip'.", "Błąd działania to 'Slip'.")
        ))
        // 3.4
        neboshDao.insertQuestion(Question("q3_4", "3.4",
            LocalizedText("Risk nedir?", "What is Risk?", "Was ist Risiko?", "Co to jest Ryzyko?"),
            Gson().toJson(listOf(LocalizedText("Zarar verme potansiyeli", "Potential to harm", "Schadenspotenzial", "Potencjał szkody"), LocalizedText("Olasılık x Şiddet", "Likelihood x Severity", "Wahrscheinlichkeit x Schwere", "Prawdopodobieństwo x Dotkliwość"), LocalizedText("Bir kaza türü", "Accident type", "Unfallart", "Rodzaj wypadku"), LocalizedText("Yasal bir ceza", "Legal fine", "Geldstrafe", "Grzywna"))),
            1, LocalizedText("Risk, gerçekleşme olasılığı ile sonucun şiddetinin çarpımıdır.", "Risk = Likelihood x Severity.", "Risiko = W x S.", "Ryzyko = P x D.")
        ))
        // 3.5 (Değişim)
        neboshDao.insertQuestion(Question("q3_5", "3.5",
            LocalizedText("Risk değerlendirmesi ne zaman güncellenmelidir?", "When to update risk assessment?", "Wann aktualisieren?", "Kiedy aktualizować?"),
            Gson().toJson(listOf(LocalizedText("Her hafta", "Every week", "Jede Woche", "Co tydzień"), LocalizedText("Önemli bir değişiklik olduğunda", "Significant change occurs", "Bei wesentlichen Änderungen", "Przy istotnych zmianach"), LocalizedText("Sadece kaza olduğunda", "Only after accidents", "Nur nach Unfällen", "Tylko po wypadkach"), LocalizedText("Hiçbir zaman", "Never", "Niemals", "Nigdy"))),
            1, LocalizedText("Değişiklik (yeni makine, yeni süreç) yeni riskler getirir.", "Change brings new risks.", "Änderungen bringen Risiken.", "Zmiany niosą ryzyko.")
        ))
        // 3.6 (SSoW)
        neboshDao.insertQuestion(Question("q3_6", "3.6",
            LocalizedText("Hangi iş için 'Çalışma İzni' (PTW) gerekir?", "Which job needs a Permit-to-Work?", "Welcher Job braucht PTW?", "Jaka praca wymaga PTW?"),
            Gson().toJson(listOf(LocalizedText("Ofis temizliği", "Office cleaning", "Büroreinigung", "Sprzątanie biura"), LocalizedText("Malzeme taşıma", "Material handling", "Materialtransport", "Transport materiałów"), LocalizedText("Kapalı alana giriş", "Confined space entry", "Einstieg in enge Räume", "Wejście do przestrzeni zamkniętej"), LocalizedText("Araba kullanma", "Driving", "Fahren", "Prowadzenie auta"))),
            2, LocalizedText("Yüksek riskli işler (Kapalı alan, sıcak iş) PTW gerektirir.", "High risk jobs need PTW.", "Hochrisikoarbeiten brauchen PTW.", "Wysokie ryzyko wymaga PTW.")
        ))
        // 3.7 (Acil Durum)
        neboshDao.insertQuestion(Question("q3_7", "3.7",
            LocalizedText("İlk yardım kutusunda ne BULUNMAMALIDIR?", "What should NOT be in a First Aid box?", "Was darf NICHT in den Kasten?", "Czego NIE powinno być w apteczce?"),
            Gson().toJson(listOf(LocalizedText("Yara bandı", "Plasters", "Pflaster", "Plastry"), LocalizedText("Sargı bezi", "Bandages", "Verbände", "Bandaże"), LocalizedText("Eldiven", "Gloves", "Handschuhe", "Rękawiczki"), LocalizedText("İlaç / Ağrı kesici", "Medication / Pills", "Medikamente", "Leki / Tabletki"))),
            3, LocalizedText("Alerji riski nedeniyle ilaç konulmaz.", "No pills due to allergy risk.", "Keine Medikamente wegen Allergierisiko.", "Brak leków z powodu ryzyka alergii.")
        ))

        // --- ELEMENT 4 ---
        // 4.1
        neboshDao.insertQuestion(Question("q4_1", "4.1",
            LocalizedText("Kaza olmadan önce yapılan kontrollere ne denir?", "Checks BEFORE an accident?", "Prüfungen VOR dem Unfall?", "Kontrole PRZED wypadkiem?"),
            Gson().toJson(listOf(LocalizedText("Reaktif İzleme", "Reactive Monitoring", "Reaktiv", "Reaktywne"), LocalizedText("Aktif İzleme", "Active Monitoring", "Aktiv", "Aktywne"), LocalizedText("Kaza Araştırması", "Investigation", "Untersuchung", "Badanie"), LocalizedText("Sağlık Gözetimi", "Health Surveillance", "Gesundheitsüberwachung", "Nadzór zdrowotny"))),
            1, LocalizedText("Aktif izleme (örn. denetim) önleyicidir.", "Active monitoring prevents accidents.", "Aktive Überwachung ist präventiv.", "Monitorowanie aktywne zapobiega.")
        ))
        // 4.2
        neboshDao.insertQuestion(Question("q4_2", "4.2",
            LocalizedText("Kaza araştırmasının asıl amacı nedir?", "Main purpose of investigation?", "Hauptzweck der Untersuchung?", "Główny cel badania?"),
            Gson().toJson(listOf(LocalizedText("Suçluyu bulmak", "Find blame", "Schuldigen finden", "Znaleźć winnego"), LocalizedText("Tekrarlanmasını önlemek", "Prevent recurrence", "Wiederholung verhindern", "Zapobiec powtórzeniu"), LocalizedText("Sigortadan para almak", "Insurance money", "Versicherungsgeld", "Pieniądze z ubezpieczenia"), LocalizedText("Rapor yazmak", "Write a report", "Bericht schreiben", "Napisać raport"))),
            1, LocalizedText("Kök nedeni bulup tekrarını önlemek.", "Find root cause to prevent recurrence.", "Grundursache finden.", "Znaleźć przyczynę źródłową.")
        ))
        // 4.3 (Denetim)
        neboshDao.insertQuestion(Question("q4_3", "4.3",
            LocalizedText("Denetim (Audit) ile Teftiş (Inspection) arasındaki fark?", "Audit vs Inspection difference?", "Unterschied Audit/Inspektion?", "Różnica Audyt/Inspekcja?"),
            Gson().toJson(listOf(LocalizedText("Fark yoktur", "No difference", "Kein Unterschied", "Brak różnicy"), LocalizedText("Denetim sisteme, Teftiş sahaya bakar", "Audit checks System, Inspection checks Site", "Audit prüft System, Inspektion prüft Ort", "Audyt sprawdza System, Inspekcja sprawdza Miejsce"), LocalizedText("Teftiş daha pahalıdır", "Inspection is expensive", "Inspektion ist teuer", "Inspekcja jest droga"), LocalizedText("Denetim her gün yapılır", "Audit is daily", "Audit ist täglich", "Audyt jest codzienny"))),
            1, LocalizedText("Denetim yönetim sistemini, teftiş fiziksel koşulları inceler.", "Audit reviews the system; Inspection checks hazards.", "Audit prüft das System.", "Audyt sprawdza system.")
        ))
        // 4.4
        neboshDao.insertQuestion(Question("q4_4", "4.4",
            LocalizedText("Yönetim gözden geçirmesi ne sıklıkla yapılmalıdır?", "How often Management Review?", "Wie oft Management Review?", "Jak często Przegląd Zarządzania?"),
            Gson().toJson(listOf(LocalizedText("Her gün", "Daily", "Täglich", "Codziennie"), LocalizedText("Her hafta", "Weekly", "Wöchentlich", "Co tydzień"), LocalizedText("Genellikle yılda bir", "Usually Annually", "Jährlich", "Rocznie"), LocalizedText("Sadece kaza olunca", "Only after accidents", "Nur nach Unfällen", "Tylko po wypadkach"))),
            2, LocalizedText("Stratejik gözden geçirme genellikle yıllıktır.", "Strategic reviews are usually annual.", "Meistens jährlich.", "Zwykle rocznie.")
        ))

        // --- ELEMENT 5 ---
        // 5.1
        neboshDao.insertQuestion(Question("q5_1", "5.1",
            LocalizedText("Gürültüde 'Üst Eylem Değeri' kaçtır?", "Noise Upper Action Value?", "Lärm Oberer Auslösewert?", "Hałas Górny Próg?"),
            Gson().toJson(listOf(LocalizedText("80 dB(A)", "80 dB(A)", "80 dB(A)", "80 dB(A)"), LocalizedText("85 dB(A)", "85 dB(A)", "85 dB(A)", "85 dB(A)"), LocalizedText("87 dB(A)", "87 dB(A)", "87 dB(A)", "87 dB(A)"), LocalizedText("140 dB(C)", "140 dB(C)", "140 dB(C)", "140 dB(C)"))),
            1, LocalizedText("85 dB(A) üst eylem değeridir, koruyucu zorunludur.", "85 dB(A) requires mandatory protection.", "85 dB(A) ist Pflicht.", "85 dB(A) jest obowiązkowe.")
        ))
        // 5.2
        neboshDao.insertQuestion(Question("q5_2", "5.2",
            LocalizedText("HAVS (El-Kol Titreşim Sendromu) belirtisi nedir?", "Symptom of HAVS?", "Symptom von HAVS?", "Objaw HAVS?"),
            Gson().toJson(listOf(LocalizedText("Mide bulantısı", "Nausea", "Übelkeit", "Mdłości"), LocalizedText("Parmakların beyazlaması", "Blanching of fingers", "Weiße Finger", "Bielenie palców"), LocalizedText("Baş ağrısı", "Headache", "Kopfschmerzen", "Ból głowy"), LocalizedText("Sırt ağrısı", "Back pain", "Rückenschmerzen", "Ból pleców"))),
            1, LocalizedText("Titreşim kan damarlarına zarar verir (Beyaz Parmak).", "Vibration damages blood vessels (White Finger).", "Weißfingerkrankheit.", "Choroba białych palców.")
        ))
        // 5.3
        neboshDao.insertQuestion(Question("q5_3", "5.3",
            LocalizedText("Radyasyondan korunmanın 3 kuralı?", "3 rules of Radiation protection?", "3 Regeln Strahlenschutz?", "3 zasady ochrony przed promieniowaniem?"),
            Gson().toJson(listOf(LocalizedText("Zaman, Mesafe, Zırhlama", "Time, Distance, Shielding", "Zeit, Abstand, Abschirmung", "Czas, Odległość, Osłona"), LocalizedText("Gözlük, Eldiven, Baret", "Glasses, Gloves, Helmet", "Brille...", "Okulary..."), LocalizedText("Su, Sabun, Havlu", "Water, Soap, Towel", "Wasser...", "Woda..."), LocalizedText("Dur, Bak, Dinle", "Stop, Look, Listen", "Stopp...", "Stop..."))),
            0, LocalizedText("Maruziyeti azalt (Zaman), Uzak dur (Mesafe), Bariyer koy (Zırhlama).", "Reduce Time, Increase Distance, Use Shielding.", "Zeit, Abstand, Abschirmung.", "Czas, Odległość, Osłona.")
        ))
        // 5.4
        neboshDao.insertQuestion(Question("q5_4", "5.4",
            LocalizedText("İşyeri stresinin bir nedeni nedir?", "Cause of workplace stress?", "Ursache für Stress?", "Przyczyna stresu?"),
            Gson().toJson(listOf(LocalizedText("Yeterli ücret", "Adequate pay", "Guter Lohn", "Dobra płaca"), LocalizedText("Aşırı iş yükü", "Excessive workload", "Überlastung", "Nadmierne obciążenie"), LocalizedText("İyi arkadaşlar", "Good friends", "Gute Freunde", "Dobrzy przyjaciele"), LocalizedText("Güvenli ortam", "Safe environment", "Sichere Umgebung", "Bezpieczne środowisko"))),
            1, LocalizedText("Aşırı talep ve kontrol eksikliği stres yaratır.", "High demands cause stress.", "Hohe Anforderungen verursachen Stress.", "Wysokie wymagania powodują stres.")
        ))
        // 5.5
        neboshDao.insertQuestion(Question("q5_5", "5.5",
            LocalizedText("Madde bağımlılığı belirtisi olabilir?", "Sign of substance abuse?", "Anzeichen für Drogenmissbrauch?", "Oznaka nadużywania substancji?"),
            Gson().toJson(listOf(LocalizedText("Erken gelmek", "Arriving early", "Früh kommen", "Wczesne przybycie"), LocalizedText("Yüksek performans", "High performance", "Hohe Leistung", "Wysoka wydajność"), LocalizedText("Ani ruh hali değişimleri", "Mood swings", "Stimmungsschwankungen", "Wahania nastroju"), LocalizedText("Temiz giyinmek", "Clean clothes", "Saubere Kleidung", "Czyste ubranie"))),
            2, LocalizedText("Davranış ve kişilik değişiklikleri belirtidir.", "Behavioral changes are signs.", "Verhaltensänderungen.", "Zmiany zachowania.")
        ))

        // --- ELEMENT 6 ---
        // 6.1
        neboshDao.insertQuestion(Question("q6_1", "6.1",
            LocalizedText("DSE (Ekranlı Araçlar) kullanırken mola düzeni nasıl olmalı?", "Break pattern for DSE?", "Pausen bei DSE?", "Przerwy przy DSE?"),
            Gson().toJson(listOf(LocalizedText("Hiç mola verilmemeli", "No breaks", "Keine Pausen", "Brak przerw"), LocalizedText("Uzun ve seyrek molalar", "Long infrequent breaks", "Lange seltene Pausen", "Długie rzadkie przerwy"), LocalizedText("Kısa ve sık molalar", "Short frequent breaks", "Kurze häufige Pausen", "Krótkie częste przerwy"), LocalizedText("Sadece öğle yemeği", "Lunch only", "Nur Mittagessen", "Tylko lunch"))),
            2, LocalizedText("Ekrandan uzaklaşmak için kısa ve sık molalar iyidir.", "Short frequent breaks are best.", "Kurz und häufig.", "Krótkie i częste.")
        ))
        // 6.2
        neboshDao.insertQuestion(Question("q6_2", "6.2",
            LocalizedText("Elle taşıma için risk değerlendirme kısaltması?", "Manual handling acronym?", "Akronym?", "Akronim?"),
            Gson().toJson(listOf(LocalizedText("TILE", "TILE", "TILE", "TILE"), LocalizedText("FIRE", "FIRE", "FIRE", "FIRE"), LocalizedText("COSHH", "COSHH", "COSHH", "COSHH"), LocalizedText("RIDDOR", "RIDDOR", "RIDDOR", "RIDDOR"))),
            0, LocalizedText("Task, Individual, Load, Environment.", "Görev, Birey, Yük, Çevre.", "Aufgabe, Individuum, Last, Umwelt.", "Zadanie, Jednostka, Ładunek, Środowisko.")
        ))
        // 6.3
        neboshDao.insertQuestion(Question("q6_3", "6.3",
            LocalizedText("Forkliftlerde en büyük risk nedir?", "Biggest risk with Forklifts?", "Größtes Risiko Stapler?", "Największe ryzyko wózków?"),
            Gson().toJson(listOf(LocalizedText("Lastik patlaması", "Flat tyre", "Reifenpanne", "Przebita opona"), LocalizedText("Devrilme", "Overturning", "Umkippen", "Przewrócenie"), LocalizedText("Yakıt bitmesi", "Running out of fuel", "Treibstoffmangel", "Brak paliwa"), LocalizedText("Gürültü", "Noise", "Lärm", "Hałas"))),
            1, LocalizedText("Dengesiz yük veya hız nedeniyle devrilme.", "Overturning due to instability.", "Umkippen.", "Przewrócenie.")
        ))

        // --- ELEMENT 7 ---
        // 7.1
        neboshDao.insertQuestion(Question("q7_1", "7.1",
            LocalizedText("GHS 'Alev' sembolü ne anlama gelir?", "GHS 'Flame' symbol?", "Flammen-Symbol?", "Symbol płomienia?"),
            Gson().toJson(listOf(LocalizedText("Toksik", "Toxic", "Giftig", "Toksyczne"), LocalizedText("Yanıcı", "Flammable", "Entzündbar", "Łatwopalne"), LocalizedText("Aşındırıcı", "Corrosive", "Ätzend", "Żrące"), LocalizedText("Patlayıcı", "Explosive", "Explosiv", "Wybuchowe"))),
            1, LocalizedText("Alev, yanıcı (flammable) maddeleri gösterir.", "Flame means Flammable.", "Flamme bedeutet entzündbar.", "Płomień oznacza łatwopalne.")
        ))
        // 7.2
        neboshDao.insertQuestion(Question("q7_2", "7.2",
            LocalizedText("Akut sağlık etkisi nedir?", "What is an Acute effect?", "Akuter Effekt?", "Skutek ostry?"),
            Gson().toJson(listOf(LocalizedText("Uzun sürede gelişen (Kanser)", "Long term (Cancer)", "Langfristig", "Długoterminowy"), LocalizedText("Hemen ortaya çıkan (Yanık)", "Immediate (Burn)", "Sofortig", "Natychmiastowy"), LocalizedText("Genetik", "Genetic", "Genetisch", "Genetyczny"), LocalizedText("Psikolojik", "Psychological", "Psychisch", "Psychologiczny"))),
            1, LocalizedText("Akut etkiler hemen görülür (kısa süreli, yüksek doz).", "Acute is immediate.", "Akut ist sofortig.", "Ostre są natychmiastowe.")
        ))
        // 7.3
        neboshDao.insertQuestion(Question("q7_3", "7.3",
            LocalizedText("LEV sisteminin parçası değildir?", "NOT part of LEV?", "Kein Teil von LEV?", "NIE część LEV?"),
            Gson().toJson(listOf(LocalizedText("Davlumbaz (Hood)", "Hood", "Haube", "Okap"), LocalizedText("Filtre", "Filter", "Filter", "Filtr"), LocalizedText("Fan", "Fan", "Ventilator", "Wentylator"), LocalizedText("Emniyet Kemeri", "Safety Harness", "Sicherheitsgurt", "Szelki bezpieczeństwa"))),
            3, LocalizedText("Emniyet kemeri yüksekte çalışmada kullanılır.", "Harness is for height.", "Gurt ist für Höhe.", "Szelki są do wysokości.")
        ))
        // 7.4
        neboshDao.insertQuestion(Question("q7_4", "7.4",
            LocalizedText("Asbest hangi hastalığa neden olur?", "Asbestos causes?", "Asbest verursacht?", "Azbest powoduje?"),
            Gson().toJson(listOf(LocalizedText("Dermatit", "Dermatitis", "Dermatitis", "Zapalenie skóry"), LocalizedText("Astım", "Asthma", "Asthma", "Astmę"), LocalizedText("Mezotelyoma (Kanser)", "Mesothelioma", "Mesotheliom", "Międzybłoniaka"), LocalizedText("İşitme kaybı", "Hearing loss", "Hörverlust", "Utratę słuchu"))),
            2, LocalizedText("Asbest akciğer kanseri ve mezotelyoma yapar.", "Causes lung cancer/mesothelioma.", "Lungenkrebs.", "Rak płuc.")
        ))

        // --- ELEMENT 8 ---
        // 8.1
        neboshDao.insertQuestion(Question("q8_1", "8.1",
            LocalizedText("İşyeri refahı (Welfare) neleri kapsar?", "Welfare includes?", "Wohlfahrt umfasst?", "Dobrostan obejmuje?"),
            Gson().toJson(listOf(LocalizedText("Maaşlar", "Wages", "Löhne", "Płace"), LocalizedText("Tuvalet ve içme suyu", "Toilets & Water", "Toiletten", "Toalety"), LocalizedText("Makineler", "Machines", "Maschinen", "Maszyny"), LocalizedText("Müşteriler", "Customers", "Kunden", "Klienci"))),
            1, LocalizedText("Refah: Tuvalet, su, yemek yeme alanı vb.", "Toilets, water, rest areas.", "Toiletten, Wasser.", "Toalety, woda.")
        ))
        // 8.2
        neboshDao.insertQuestion(Question("q8_2", "8.2",
            LocalizedText("Kaymaları (Slips) önlemenin en iyi yolu?", "Prevent slips?", "Ausrutschen verhindern?", "Zapobiec poślizgnięciom?"),
            Gson().toJson(listOf(LocalizedText("Dökülenleri hemen temizlemek", "Clean spills immediately", "Sofort reinigen", "Natychmiast czyścić"), LocalizedText("Daha hızlı yürümek", "Walk faster", "Schneller gehen", "Chodzić szybciej"), LocalizedText("Işıkları kapatmak", "Turn off lights", "Licht aus", "Wyłączyć światła"), LocalizedText("Görmezden gelmek", "Ignore it", "Ignorieren", "Ignorować"))),
            0, LocalizedText("Zemin temizliği ve uygun ayakkabı şarttır.", "Housekeeping is key.", "Sauberkeit ist wichtig.", "Czystość jest kluczowa.")
        ))
        // 8.3
        neboshDao.insertQuestion(Question("q8_3", "8.3",
            LocalizedText("Yüksekte çalışma için öncelik sırası?", "Hierarchy for height?", "Hierarchie Höhe?", "Hierarchia wysokości?"),
            Gson().toJson(listOf(LocalizedText("KKD kullan - Kaçın - Önle", "PPE - Avoid - Prevent", "PSA...", "ŚOI..."), LocalizedText("Kaçın - Önle - Azalt", "Avoid - Prevent - Minimise", "Vermeiden - Verhindern - Minimieren", "Unikaj - Zapobiegaj - Minimalizuj"), LocalizedText("Atla - Koş - Dur", "Jump - Run - Stop", "Springen...", "Skacz..."), LocalizedText("Önlem alma", "Do nothing", "Nichts tun", "Nic nie rób"))),
            1, LocalizedText("Önce işi yerde yap (Kaçın), olmazsa düşmeyi önle.", "Avoid work at height first.", "Zuerst vermeiden.", "Najpierw unikaj.")
        ))
        // 8.4
        neboshDao.insertQuestion(Question("q8_4", "8.4",
            LocalizedText("Kapalı alandaki en büyük tehlike?", "Main hazard in confined space?", "Hauptgefahr enger Raum?", "Główne zagrożenie?"),
            Gson().toJson(listOf(LocalizedText("Gürültü", "Noise", "Lärm", "Hałas"), LocalizedText("Oksijen eksikliği / Zehirli gaz", "Oxygen lack / Toxic gas", "Sauerstoffmangel", "Brak tlenu"), LocalizedText("Toz", "Dust", "Staub", "Pył"), LocalizedText("Titreşim", "Vibration", "Vibration", "Wibracje"))),
            1, LocalizedText("Havasızlık veya zehirli gazlar anında öldürebilir.", "Atmosphere kills.", "Atmosphäre tötet.", "Atmosfera zabija.")
        ))
        // 8.5
        neboshDao.insertQuestion(Question("q8_5", "8.5",
            LocalizedText("Yalnız çalışanlar (Lone Workers) için kontrol?", "Control for Lone Workers?", "Kontrolle Alleinarbeit?", "Kontrola samotnych?"),
            Gson().toJson(listOf(LocalizedText("Yanına köpek vermek", "Give a dog", "Hund", "Dać psa"), LocalizedText("Düzenli iletişim/kontrol", "Regular contact/check-ins", "Regelmäßiger Kontakt", "Regularny kontakt"), LocalizedText("Daha fazla maaş", "More pay", "Mehr Lohn", "Więcej płacy"), LocalizedText("Silah vermek", "Give a gun", "Waffe", "Dać broń"))),
            1, LocalizedText("Düzenli aralıklarla iletişim kurulmalıdır.", "Must maintain contact.", "Kontakt halten.", "Utrzymywać kontakt.")
        ))
        // 8.6
        neboshDao.insertQuestion(Question("q8_6", "8.6",
            LocalizedText("İş amaçlı araç sürüşü neyi KAPSAMAZ?", "Work driving excludes?", "Schließt was aus?", "Wyklucza co?"),
            Gson().toJson(listOf(LocalizedText("Müşteri ziyareti", "Client visit", "Kundenbesuch", "Wizyta u klienta"), LocalizedText("Mal teslimatı", "Delivery", "Lieferung", "Dostawa"), LocalizedText("Evden işe gidip gelme (Commuting)", "Commuting to work", "Pendeln", "Dojazdy do pracy"), LocalizedText("Kamyon sürme", "Driving truck", "LKW fahren", "Jazda ciężarówką"))),
            2, LocalizedText("Evden işe gidiş-geliş işverenin sorumluluğunda değildir.", "Commuting is not work driving.", "Pendeln ist privat.", "Dojazdy to prywatne.")
        ))

        // --- ELEMENT 9 ---
        // 9.1
        neboshDao.insertQuestion(Question("q9_1", "9.1",
            LocalizedText("Makinenin tehlikeli kısmına teması engelleyen parça?", "Prevents contact with danger?", "Verhindert Kontakt?", "Zapobiega kontaktowi?"),
            Gson().toJson(listOf(LocalizedText("Motor", "Motor", "Motor", "Silnik"), LocalizedText("Koruyucu (Guard)", "Guard", "Schutz", "Osłona"), LocalizedText("Tekerlek", "Wheel", "Rad", "Koło"), LocalizedText("Kablo", "Cable", "Kabel", "Kabel"))),
            1, LocalizedText("Koruyucular (Guards) fiziksel engeldir.", "Guards are physical barriers.", "Schutzvorrichtungen.", "Osłony.")
        ))
        // 9.2
        neboshDao.insertQuestion(Question("q9_2", "9.2",
            LocalizedText("Taşınabilir aletlerde elektrik riskini azaltmak için voltaj ne olmalı?", "Voltage for portable tools?", "Spannung für Werkzeuge?", "Napięcie dla narzędzi?"),
            Gson().toJson(listOf(LocalizedText("240V", "240V", "240V", "240V"), LocalizedText("110V", "110V", "110V", "110V"), LocalizedText("415V", "415V", "415V", "415V"), LocalizedText("1000V", "1000V", "1000V", "1000V"))),
            1, LocalizedText("Şantiyelerde 110V (sarı trafo) kullanılır.", "110V reduced voltage is safer.", "110V ist sicherer.", "110V jest bezpieczniejsze.")
        ))
        // 9.3
        neboshDao.insertQuestion(Question("q9_3", "9.3",
            LocalizedText("Dönen bir parçanın kıyafeti yakalaması hangi tehlikedir?", "Clothes caught in rotation?", "Kleidung in Rotation?", "Ubranie w rotacji?"),
            Gson().toJson(listOf(LocalizedText("Kesme", "Cutting", "Schneiden", "Cięcie"), LocalizedText("Dolanma / Kapılma", "Entanglement", "Erfassen", "Pochwycenie"), LocalizedText("Yanma", "Burning", "Verbrennen", "Oparzenie"), LocalizedText("Ezilme", "Crushing", "Quetschen", "Zgniecenie"))),
            1, LocalizedText("Dönen miller dolanma (entanglement) riski yaratır.", "Rotating parts cause entanglement.", "Erfassen.", "Pochwycenie.")
        ))
        // 9.4
        neboshDao.insertQuestion(Question("q9_4", "9.4",
            LocalizedText("Acil durdurma butonu (E-Stop) ne renktir?", "Colour of E-Stop?", "Farbe Not-Halt?", "Kolor E-Stop?"),
            Gson().toJson(listOf(LocalizedText("Yeşil", "Green", "Grün", "Zielony"), LocalizedText("Sarı arka plan üzerine Kırmızı", "Red on Yellow background", "Rot auf Gelb", "Czerwony na żółtym"), LocalizedText("Mavi", "Blue", "Blau", "Niebieski"), LocalizedText("Siyah", "Black", "Schwarz", "Czarny"))),
            1, LocalizedText("Standart: Sarı zemin üzerine kırmızı mantar buton.", "Red button on yellow background.", "Rot auf Gelb.", "Czerwony na żółtym.")
        ))

        // --- ELEMENT 10 ---
        // 10.1
        neboshDao.insertQuestion(Question("q10_1", "10.1",
            LocalizedText("Yangın üçgeninden 'Isı'yı almak hangi söndürme yöntemidir?", "Removing Heat?", "Wärme entfernen?", "Usuwanie ciepła?"),
            Gson().toJson(listOf(LocalizedText("Boğma", "Smothering", "Ersticken", "Duszenie"), LocalizedText("Soğutma", "Cooling", "Kühlen", "Chłodzenie"), LocalizedText("Yakıtı Kesme", "Starvation", "Aushungern", "Głodzenie"), LocalizedText("Kaçma", "Running", "Rennen", "Ucieczka"))),
            1, LocalizedText("Su kullanarak ısıyı düşürmek 'Soğutma'dır.", "Removing heat is Cooling.", "Kühlen.", "Chłodzenie.")
        ))
        // 10.2
        neboshDao.insertQuestion(Question("q10_2", "10.2",
            LocalizedText("Yangın kapısının özelliği ne olmalıdır?", "Feature of Fire Door?", "Merkmal Brandschutztür?", "Cecha drzwi PPOŻ?"),
            Gson().toJson(listOf(LocalizedText("Kilitli olmalı", "Locked", "Verschlossen", "Zamknięte"), LocalizedText("Kendiliğinden kapanmalı (Self-closing)", "Self-closing", "Selbstschließend", "Samozamykające"), LocalizedText("Camdan olmalı", "Made of glass", "Aus Glas", "Ze szkła"), LocalizedText("Açık tutulmalı", "Wedged open", "Offen gehalten", "Otwarte"))),
            1, LocalizedText("Yangın kapıları dumanı engellemek için kapalı durmalıdır.", "Must close automatically to stop smoke.", "Muss schließen.", "Musi się zamykać.")
        ))
        // 10.3
        neboshDao.insertQuestion(Question("q10_3", "10.3",
            LocalizedText("Sıvı yangınlarında (Benzin) hangi söndürücü KULLANILMAZ?", "NOT for liquid fires?", "NICHT für Flüssigkeiten?", "NIE do cieczy?"),
            Gson().toJson(listOf(LocalizedText("Köpük", "Foam", "Schaum", "Piana"), LocalizedText("Kuru Toz", "Dry Powder", "Pulver", "Proszek"), LocalizedText("Su", "Water", "Wasser", "Woda"), LocalizedText("CO2", "CO2", "CO2", "CO2"))),
            2, LocalizedText("Su, yanan sıvıyı dağıtır ve yangını büyütür.", "Water spreads the fire.", "Wasser verbreitet Feuer.", "Woda rozprzestrzenia ogień.")
        ))
        // 10.4
        neboshDao.insertQuestion(Question("q10_4", "10.4",
            LocalizedText("Yangın tatbikatı ne sıklıkla yapılmalıdır?", "Frequency of fire drills?", "Häufigkeit Übungen?", "Częstotliwość ćwiczeń?"),
            Gson().toJson(listOf(LocalizedText("Asla", "Never", "Nie", "Nigdy"), LocalizedText("Yılda en az 1 kez", "At least Annually", "Mindestens jährlich", "Przynajmniej raz w roku"), LocalizedText("Her gün", "Daily", "Täglich", "Codziennie"), LocalizedText("Sadece yangın çıkınca", "Only during fire", "Nur bei Feuer", "Tylko podczas pożaru"))),
            1, LocalizedText("Yasal zorunluluk genellikle yılda birdir.", "Usually annual requirement.", "Jährlich.", "Rocznie.")
        ))

        // --- ELEMENT 11 ---
        // 11.1
        neboshDao.insertQuestion(Question("q11_1", "11.1",
            LocalizedText("Elektriğin insan vücuduna en büyük etkisi?", "Major effect of electricity?", "Hauptwirkung?", "Główny skutek?"),
            Gson().toJson(listOf(LocalizedText("Gıdıklanma", "Tickling", "Kitzeln", "Łaskotanie"), LocalizedText("Şok ve Yanık", "Shock and Burns", "Schlag und Verbrennung", "Wstrząs i Oparzenia"), LocalizedText("Açlık", "Hunger", "Hunger", "Głód"), LocalizedText("Uykusuzluk", "Insomnia", "Schlaflosigkeit", "Bezsenność"))),
            1, LocalizedText("Elektrik şoku kalbi durdurabilir, yanıklar dokuyu bozar.", "Shock stops heart, burns damage tissue.", "Schlag und Brand.", "Wstrząs i oparzenia.")
        ))
        // 11.2
        neboshDao.insertQuestion(Question("q11_2", "11.2",
            LocalizedText("Sigortanın (Fuse) asıl amacı nedir?", "Purpose of a Fuse?", "Zweck der Sicherung?", "Cel bezpiecznika?"),
            Gson().toJson(listOf(LocalizedText("İnsanı korumak", "Protect human", "Mensch schützen", "Chronić człowieka"), LocalizedText("Cihazı aşırı akımdan korumak", "Protect equipment from overcurrent", "Gerät schützen", "Chronić sprzęt"), LocalizedText("Voltajı düşürmek", "Reduce voltage", "Spannung senken", "Obniżyć napięcie"), LocalizedText("Elektrik üretmek", "Generate power", "Strom erzeugen", "Wytwarzać prąd"))),
            1, LocalizedText("Sigorta cihazı korur, RCD insanı korur.", "Fuse protects appliance/cable.", "Sicherung schützt Gerät.", "Bezpiecznik chroni sprzęt.")
        ))

        // --- ELEMENT 12 (IG2) ---
        // 12.1
        neboshDao.insertQuestion(Question("q12_1", "12.1",
            LocalizedText("IG2 projesinin başarısız olma nedeni?", "Reason for IG2 failure?", "Grund für Durchfallen?", "Powód niezaliczenia?"),
            Gson().toJson(listOf(LocalizedText("Çok uzun yazmak", "Writing too much", "Zu viel schreiben", "Pisać za dużo"), LocalizedText("Tehlikeleri belirlememek", "Not identifying hazards", "Gefahren nicht erkennen", "Nie zidentyfikować zagrożeń"), LocalizedText("Renkli kalem kullanmak", "Using coloured pens", "Bunte Stifte", "Kolorowe długopisy"), LocalizedText("Bilgisayarda yazmak", "Typing it", "Tippen", "Pisanie na komputerze"))),
            1, LocalizedText("Yeterli tehlike bulunmazsa kalırsınız.", "Must identify hazards correctly.", "Muss Gefahren erkennen.", "Musi zidentyfikować zagrożenia.")
        ))
        // 12.2
        neboshDao.insertQuestion(Question("q12_2", "12.2",
            LocalizedText("Risk değerlendirmesi tablosunda ne olmalıdır?", "What goes in risk table?", "Was kommt in die Tabelle?", "Co w tabeli?"),
            Gson().toJson(listOf(LocalizedText("Sadece tehlike adı", "Only hazard name", "Nur Name", "Tylko nazwa"), LocalizedText("Tehlike, Kim, Nasıl, Önlemler", "Hazard, Who, How, Controls", "Gefahr, Wer, Wie, Kontrollen", "Zagrożenie, Kto, Jak, Kontrole"), LocalizedText("Şirket tarihçesi", "Company history", "Firmengeschichte", "Historia firmy"), LocalizedText("Hava durumu", "Weather", "Wetter", "Pogoda"))),
            1, LocalizedText("Tablo tüm detayları (Tehlike, Kim, Mevcut Önlem, Ek Önlem) içermelidir.", "Full details required.", "Alle Details.", "Pełne szczegóły.")
        ))
        // 12.3
        neboshDao.insertQuestion(Question("q12_3", "12.3",
            LocalizedText("Yönetime raporda hangi argümanlar kullanılır?", "Arguments for management?", "Argumente für Management?", "Argumenty dla zarządu?"),
            Gson().toJson(listOf(LocalizedText("Ahlaki, Yasal, Finansal", "Moral, Legal, Financial", "Moralisch...", "Moralne..."), LocalizedText("Dini, Siyasi, Spor", "Religious, Political, Sport", "Religiös...", "Religijne..."), LocalizedText("Sadece Finansal", "Financial Only", "Nur Finanzen", "Tylko Finansowe"), LocalizedText("Hiçbiri", "None", "Keine", "Żadne"))),
            0, LocalizedText("Yönetimi ikna etmek için 3 temel argüman kullanılır.", "3 key arguments.", "3 Argumente.", "3 argumenty.")
        ))
    }
    // 4. SÖZLÜK VERİLERİ (A-Z NEBOSH TERİMLERİ)
    private suspend fun insertGlossaryTerms() {

        neboshDao.deleteAllTerms()

        val terms = listOf(
            GlossaryTerm("t1", "Accident",
                "Kaza: İstenmeyen, planlanmayan ve yaralanma veya hasarla sonuçlanan olay.",
                "Unwanted, unplanned event resulting in injury or damage.",
                "Unfall: Unerwünschtes Ereignis mit Schaden.",
                "Wypadek: Niepożądane zdarzenie powodujące szkodę."
            ),
            GlossaryTerm("t2", "Acute Health Effect",
                "Akut Sağlık Etkisi: Yüksek doza kısa süreli maruziyet sonucu hemen ortaya çıkan etki (Örn: Asit yanığı).",
                "Short term exposure, high dose, immediate effect.",
                "Akuter Effekt: Sofortige Wirkung.",
                "Ostry skutek zdrowotny: Natychmiastowy efekt."
            ),
            GlossaryTerm("t3", "Audit",
                "Denetim: Yönetim sisteminin sistematik, objektif ve kritik bir şekilde incelenmesi.",
                "Systematic, objective, critical review of the management system.",
                "Audit: Systematische Überprüfung.",
                "Audyt: Systematyczny przegląd."
            ),
            GlossaryTerm("t4", "Chronic Health Effect",
                "Kronik Sağlık Etkisi: Düşük doza uzun süreli maruziyet sonucu zamanla gelişen etki (Örn: Kanser).",
                "Long term exposure, low dose, slow development.",
                "Chronischer Effekt: Langzeitwirkung.",
                "Przewlekły skutek: Długoterminowy efekt."
            ),
            GlossaryTerm("t5", "Competence",
                "Yetkinlik: Bilgi, Beceri, Deneyim ve Eğitimin birleşimi (KATE).",
                "Combination of Knowledge, Ability, Training, and Experience (KATE).",
                "Kompetenz: Wissen, Können, Erfahrung.",
                "Kompetencje: Wiedza, Umiejętności, Doświadczenie."
            ),
            GlossaryTerm("t6", "Confined Space",
                "Kapalı Alan: Giriş çıkışı kısıtlı, içinde tehlikeli madde veya oksijen eksikliği riski olan yer.",
                "Enclosed area with foreseeable risk of injury (fumes, low O2).",
                "Enger Raum: Begrenzter Zugang, Gefahren.",
                "Przestrzeń zamknięta: Ograniczony dostęp, ryzyko."
            ),
            GlossaryTerm("t7", "Duty of Care",
                "Özen Gösterme Borcu: Başkalarına zarar vermemek için makul özen gösterme yasal yükümlülüğü.",
                "Legal obligation to take reasonable care not to harm others.",
                "Sorgfaltspflicht.",
                "Obowiązek opieki."
            ),
            GlossaryTerm("t8", "Ergonomics",
                "Ergonomi: İşin, ekipmanın ve çevrenin işçiye uyumlu hale getirilmesi.",
                "Fitting the job to the worker.",
                "Ergonomie: Anpassung der Arbeit an den Menschen.",
                "Ergonomia: Dopasowanie pracy do pracownika."
            ),
            GlossaryTerm("t9", "Hazard",
                "Tehlike: Zarar verme potansiyeli olan herhangi bir şey (Madde, makine, yöntem).",
                "Something with the potential to cause harm.",
                "Gefahr: Schadenspotenzial.",
                "Zagrożenie: Potencjał szkody."
            ),
            GlossaryTerm("t10", "Hierarchy of Control",
                "Kontrol Hiyerarşisi: Riskleri azaltmak için izlenen öncelik sırası (Eliminasyon -> KKD).",
                "Order of priority for risk control (Eliminate -> PPE).",
                "Hierarchie der Maßnahmen.",
                "Hierarchia kontroli."
            ),
            GlossaryTerm("t11", "Incident",
                "Olay: Kaza ve 'Ramak Kala'yı kapsayan genel terim.",
                "Includes both accidents and near misses.",
                "Vorfall: Unfälle und Beinaheunfälle.",
                "Incydent: Wypadki i zdarzenia potencjalne."
            ),
            GlossaryTerm("t12", "Inspection",
                "Teftiş: İşyerindeki fiziksel tehlikelerin ve güvensiz durumların kontrol edilmesi.",
                "Checking workplace for physical hazards.",
                "Inspektion: Prüfung auf Gefahren.",
                "Inspekcja: Sprawdzanie zagrożeń fizycznych."
            ),
            GlossaryTerm("t13", "Near Miss",
                "Ramak Kala: Yaralanma veya hasar potansiyeli olan ancak gerçekleşmeyen olay.",
                "Event with potential to harm but didn't (Lucky escape).",
                "Beinaheunfall.",
                "Zdarzenie potencjalne (O włos)."
            ),
            GlossaryTerm("t14", "Negligence",
                "İhmal: Özen gösterme borcunun ihlali sonucu zarara neden olmak.",
                "Breach of duty of care resulting in damage.",
                "Fahrlässigkeit.",
                "Zaniedbanie."
            ),
            GlossaryTerm("t15", "Permit-to-Work",
                "Çalışma İzni: Yüksek riskli işlerin güvenli yapılmasını sağlayan resmi, yazılı sistem.",
                "Formal written system to control high-risk activities.",
                "Arbeitserlaubnis.",
                "Pozwolenie na pracę."
            ),
            GlossaryTerm("t16", "PPE (KKD)",
                "Kişisel Koruyucu Donanım: İşçiyi risklerden koruyan ekipman (Baret, eldiven vb.).",
                "Personal Protective Equipment (Helmet, gloves etc).",
                "Persönliche Schutzausrüstung (PSA).",
                "Środki Ochrony Indywidualnej (ŚOI)."
            ),
            GlossaryTerm("t17", "Risk",
                "Risk: Bir tehlikenin gerçekleşme olasılığı ile sonucun şiddetinin bileşimi.",
                "Likelihood of harm x Severity of harm.",
                "Risiko: Wahrscheinlichkeit x Schwere.",
                "Ryzyko: Prawdopodobieństwo x Dotkliwość."
            ),
            GlossaryTerm("t18", "Risk Assessment",
                "Risk Değerlendirmesi: Tehlikelerin belirlenmesi ve risklerin kontrol edilip edilmediğinin incelenmesi.",
                "Process of identifying hazards and evaluating risks.",
                "Risikobeurteilung.",
                "Ocena ryzyka."
            ),
            GlossaryTerm("t19", "Safe System of Work",
                "Güvenli Çalışma Sistemi: Bir işin güvenli yapılması için tanımlanmış adım adım yöntem.",
                "Formal procedure defines how to do a job safely.",
                "Sicheres Arbeitssystem.",
                "Bezpieczny system pracy."
            ),
            GlossaryTerm("t20", "SFAIRP",
                "Makul Ölçüde Uygulanabilir: Risk ile önlemin maliyeti (para/zaman) arasındaki denge.",
                "So Far As Is Reasonably Practicable (Risk vs Cost).",
                "Soweit vernünftigerweise durchführbar.",
                "O ile jest to racjonalnie wykonalne."
            )
        )

        // Hepsini veritabanına ekle
        for (term in terms) {
            neboshDao.insertTerm(term)
        }
    }
}