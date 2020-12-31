package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
//@RequiredArgsConstructor
public class SampleHotel {

    @NonNull
    private Chef chef;

    // 의존성 주입 - 생성자 주입 방법: 객체 생성 시 의존성 주입이 필요하여 좀 더 엄격한 의존성 주입을 체크하는 장점
    public SampleHotel(Chef chef) {
        this.chef = chef;
    }

    private String name;
}
