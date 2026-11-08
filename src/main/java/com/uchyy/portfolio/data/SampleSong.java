
package com.uchyy.portfolio.data;

import com.uchyy.portfolio.model.MediaType;
import com.uchyy.portfolio.model.Song;
import com.uchyy.portfolio.model.SongMedia;
import com.uchyy.portfolio.model.SongStatus;

import java.util.List;

public class SampleSong {

    private static final String SAMPLE_AUDIO =
            "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";

    public static List<Song> getSongs() {

        return List.of(

                new Song(
                        "song-001",
                        "MAD",
                        "mad",
                        "Sometimes the line between love and madness is thinner than we think.",
                        List.of("Alternative Pop", "Dark Pop"),
                        SongStatus.COMPLETED,
                        "https://placehold.co/800x800/F0E5D8/30251F?text=MAD",
                        "MAD explores the intensity of emotions when feelings become difficult to contain.",
                        List.of(
                                "The initial idea was built around the title and its emotional possibilities.",
                                "Experimented with different vocal moods and musical directions."
                        ),
                        null,
                        List.of(
                                new SongMedia(
                                        "mad-demo",
                                        MediaType.DEMO,
                                        "Main Demo",
                                        SAMPLE_AUDIO,
                                        "00:30"
                                ),
                                new SongMedia(
                                        "mad-instrumental",
                                        MediaType.INSTRUMENTAL,
                                        "Instrumental Version",
                                        SAMPLE_AUDIO,
                                        "00:30"
                                )
                        ),
                        "https://suno.com/s/ZIUXsycA1n973YVM",
                        "2026-10-01",
                        "2026-10-01",
                        true
                ),

                new Song(
                        "song-002",
                        "Cheap Perfume",
                        "cheap-perfume",
                        "A scent, a memory and something that refuses to disappear.",
                        List.of("Pop", "Alternative"),
                        SongStatus.COMPLETED,
                        "https://placehold.co/800x800/E9D9D0/30251F?text=CHEAP+PERFUME",
                        "A familiar scent becomes a reminder of someone, somewhere or something that once mattered.",
                        List.of(
                                "The title became the starting point for exploring the song's imagery.",
                                "Experimented with ways to make the central idea feel intimate and memorable."
                        ),
                        null,
                        List.of(
                                new SongMedia(
                                        "cheap-perfume-demo",
                                        MediaType.DEMO,
                                        "Main Demo",
                                        SAMPLE_AUDIO,
                                        "00:30"
                                )
                        ),
                        "https://suno.com/s/o8VduQLmW98oelaW",
                        "2026-10-05",
                        "2026-10-06",
                        true
                ),

                new Song(
                        "song-003",
                        "Lady in Red",
                        "lady-in-red",
                        "A mysterious presence wrapped in a bluesy, jazz-inspired atmosphere.",
                        List.of("Jazz", "Blues"),
                        SongStatus.IN_PROGRESS,
                        "https://placehold.co/800x800/6B302F/FFF8ED?text=LADY+IN+RED",
                        "Lady in Red creates an atmosphere around a striking figure.",
                        List.of(
                                "Started exploring a blues and jazz direction.",
                                "Experimented with a more distinctive piano sound.",
                                "Considered a slightly faster tempo while keeping the arrangement mellow."
                        ),
                        null,
                        List.of(
                                new SongMedia(
                                        "lady-in-red-demo",
                                        MediaType.DEMO,
                                        "Main Demo",
                                        SAMPLE_AUDIO,
                                        "00:30"
                                ),
                                new SongMedia(
                                        "lady-in-red-instrumental",
                                        MediaType.INSTRUMENTAL,
                                        "Instrumental",
                                        SAMPLE_AUDIO,
                                        "00:30"
                                )
                        ),
                        "https://suno.com/s/0lWoiWozyltCCORG",
                        "2026-10-08",
                        "2026-10-08",
                        true
                ),

                new Song(
                        "song-007",
                        "Thief",
                        "thief",
                        "An innocent appearance can hide a much darker story.",
                        List.of("Jazz", "Dark Pop"),
                        SongStatus.COMPLETED,
                        "https://placehold.co/800x800/44352F/E9D9C8?text=THIEF",
                        "Thief began as a poem about a woman with an almost angelic nature. The idea evolved into a darker story of obsession and revenge.",
                        List.of(
                                "The original idea started as a poem.",
                                "The character was initially imagined as someone with an angelic nature.",
                                "The narrative evolved into something darker, with obsession and revenge becoming central themes.",
                                "Explored jazz-inspired musical arrangements."
                        ),
                        null,
                        List.of(
                                new SongMedia(
                                        "thief-demo",
                                        MediaType.DEMO,
                                        "Main Demo",
                                        SAMPLE_AUDIO,
                                        "00:30"
                                ),
                                new SongMedia(
                                        "thief-instrumental",
                                        MediaType.INSTRUMENTAL,
                                        "Instrumental",
                                        SAMPLE_AUDIO,
                                        "00:30"

                                )
                        ),
                        "https://suno.com/s/tCeF5167Dmm0zN6z",
                        "2026-10-18",
                        "2026-10-18",
                        true
                )
        );
    }
}
