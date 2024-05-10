package edu.finplatjavacourse.distributeddbprototype.handler.response;


public class ReadResponseTransformer extends ResponseTransformer {
    public ReadResponseTransformer(ResponseTransformer next) {
        super(next);
    }

    public ReadResponseTransformer() {
        super(null);
    }

    @Override
    protected String transform0(Response response) {
        StringBuilder builder = new StringBuilder();

        ((ReadResponse) response).getHotels().forEach(hotel -> {
            builder.append(hotel.getId()).append(" ");
            builder.append("\"").append(hotel.getName()).append("\"").append(" ");
            builder.append(hotel.getPrice()).append("\n");
        });

        return builder.toString();
    }

    @Override
    protected boolean canTransform(Response response) {
        return response instanceof ReadResponse;
    }
}
