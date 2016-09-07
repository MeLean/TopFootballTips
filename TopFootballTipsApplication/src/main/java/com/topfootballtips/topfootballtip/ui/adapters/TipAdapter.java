package com.topfootballtips.topfootballtip.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.topfootballtips.topfootballtip.R;
import com.topfootballtips.topfootballtip.api.api_models.BestTip;
import com.topfootballtips.topfootballtip.constants.CountryName;
import com.topfootballtips.topfootballtip.constants.Preferences;

import java.util.List;


public class TipAdapter extends ArrayAdapter<BestTip> {
    private Context mContext;
    private int mResource;

    public TipAdapter(Context context, int elementResource, List<BestTip> tips) {
        super(context, elementResource,tips);
        this.mContext = context;
        this.mResource = elementResource;
    }

    private static class ViewHolderItem {
        ImageView countyImage;
        TextView county;
        TextView match;
        TextView matchResult;
        TextView matchDate;
        TextView coef;
        TextView tip;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;

        if(convertView == null){

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mResource, parent, false);

            viewHolder = new ViewHolderItem();
            viewHolder.countyImage = (ImageView) convertView.findViewById(R.id.img_country_flag);
            viewHolder.county = (TextView) convertView.findViewById(R.id.tw_country);
            viewHolder.match = (TextView) convertView.findViewById(R.id.tw_match);
            viewHolder.matchResult = (TextView) convertView.findViewById(R.id.tw_result);
            viewHolder.matchDate = (TextView) convertView.findViewById(R.id.tw_match_date);
            viewHolder.coef = (TextView) convertView.findViewById(R.id.tw_coef);
            viewHolder.tip = (TextView) convertView.findViewById(R.id.tw_tip);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        // object item based on the position
        BestTip tip = getItem(position);

        // assign values if the object is not null
        if(tip != null) {
            // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
            String countryText = tip.getCountry().trim();
            this.setImageTo(viewHolder.countyImage, countryText);
            viewHolder.county.setText(countryText);
            viewHolder.match.setText(tip.getMatch());
            viewHolder.matchResult.setText(tip.getResult());
            viewHolder.matchDate.setText(tip.getMatchDate());
            viewHolder.coef.setText(tip.getCoef());
            viewHolder.tip.setText(tip.getTip());

            int tipHit = 0;
            try {
                tipHit = tip.getTipResult();
            }catch (NumberFormatException e){
                e.printStackTrace();
            }

            if(tipHit == Preferences.TIP_IS_WAITING_FOR_RESULT) {
                viewHolder.tip.setBackgroundResource(R.drawable.shape_neutral_guessed_result);
            }else if(tipHit == Preferences.TIP_IS_GUESSED){
                viewHolder.tip.setBackgroundResource(R.drawable.shape_guessed_result);
            }else if(tipHit == Preferences.TIP_IS_NOT_GUESSED){
                viewHolder.tip.setBackgroundResource(R.drawable.shape_not_guessed_result);
            }
        }

        return convertView;
    }

    private void setImageTo(ImageView county_img, String countryText) {
        if (countryText.equalsIgnoreCase(CountryName.ARGENTINA)){
            county_img.setImageResource(R.drawable.ic_argentina);
        }else if(countryText.equalsIgnoreCase(CountryName.AUSTRALIA)){
            county_img.setImageResource(R.drawable.ic_australia);
        }else if(countryText.equalsIgnoreCase(CountryName.BRAZIL)){
            county_img.setImageResource(R.drawable.ic_brazil);
        }else if(countryText.equalsIgnoreCase(CountryName.BULGARIA)){
            county_img.setImageResource(R.drawable.ic_bulgaria);
        } else if(countryText.equalsIgnoreCase(CountryName.CHAMPIONS_LEAGUE)){
            county_img.setImageResource(R.drawable.ic_champions_league2);
        } else if(countryText.equalsIgnoreCase(CountryName.DENMARK)){
            county_img.setImageResource(R.drawable.ic_denmark);
        }else if(countryText.equalsIgnoreCase(CountryName.ENGLAND)){
            county_img.setImageResource(R.drawable.ic_england);
        } else if(countryText.equalsIgnoreCase(CountryName.EUROPA_LEAGUE)){
            county_img.setImageResource(R.drawable.ic_europaleague);
        } else if(countryText.equalsIgnoreCase(CountryName.FRANCE)){
            county_img.setImageResource(R.drawable.ic_france);
        } else if(countryText.equalsIgnoreCase(CountryName.GERMANY)){
            county_img.setImageResource(R.drawable.ic_germany);
        } else if(countryText.equalsIgnoreCase(CountryName.GREECE)){
            county_img.setImageResource(R.drawable.ic_greece);
        } else if(countryText.equalsIgnoreCase(CountryName.ITALY)){
            county_img.setImageResource(R.drawable.ic_italy);
        } else if(countryText.equalsIgnoreCase(CountryName.NETHERLANDS)){
            county_img.setImageResource(R.drawable.ic_netherlands);
        } else if(countryText.equalsIgnoreCase(CountryName.PORTUGAL)){
            county_img.setImageResource(R.drawable.ic_portugal);
        } else if(countryText.equalsIgnoreCase(CountryName.RUSSIA)){
            county_img.setImageResource(R.drawable.ic_russia);
        } else if(countryText.equalsIgnoreCase(CountryName.SERBIA)){
            county_img.setImageResource(R.drawable.ic_serbia);
        } else if(countryText.equalsIgnoreCase(CountryName.SLOVAKIA)){
            county_img.setImageResource(R.drawable.ic_slovakia);
        } else if(countryText.equalsIgnoreCase(CountryName.SLOVENIA)){
            county_img.setImageResource(R.drawable.ic_slovenia);
        } else if(countryText.equalsIgnoreCase(CountryName.SPAIN)){
            county_img.setImageResource(R.drawable.ic_spain);
        } else if(countryText.equalsIgnoreCase(CountryName.SWITZERLAND)){
            county_img.setImageResource(R.drawable.ic_switzerland);
        } else if(countryText.equalsIgnoreCase(CountryName.USA)){
            county_img.setImageResource(R.drawable.ic_united_states);
        } else {
            county_img.setImageResource(R.drawable.ic_default_img);
        }
    }
}

